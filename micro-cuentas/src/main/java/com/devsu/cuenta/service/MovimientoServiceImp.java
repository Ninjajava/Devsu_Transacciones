package com.devsu.cuenta.service;

import com.devsu.cuenta.controller.InformacionException;
import com.devsu.cuenta.dto.MovimientoDto;
import com.devsu.cuenta.mapper.MapperDatos;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.model.Movimientos;
import com.devsu.cuenta.repository.CuentaRepository;
import com.devsu.cuenta.repository.MovimientosRepository;
import com.devsu.cuenta.utils.Constantes;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoServiceImp implements MovimientoService{

    private  MovimientosRepository repository;
    private  CuentaRepository ctaRepository;
    private  MapperDatos mapper;

    public MovimientoServiceImp(MovimientosRepository repository, CuentaRepository ctaRepository, MapperDatos mapper) {
        this.repository = repository;
        this.ctaRepository = ctaRepository;
        this.mapper = mapper;
    }

    @Override
    public MovimientoDto registraMovimiento(Movimientos movimientos) {
        Cuenta cta = ctaRepository.findById(movimientos.getCuenta().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta Id no regitra en el sistema"));
        tipoMovimiento(movimientos.getTipoMovimiento(), cta,movimientos);
        movimientos.setFecha(LocalDateTime.now());
        movimientos.setSaldo(cta.getSaldoInicial());
        return mapper.mapperMovimientos(repository.save(movimientos)) ;
    }


    private Cuenta tipoMovimiento(String tipoMovimiento, Cuenta cta, Movimientos movimientos){
        if(tipoMovimiento.equalsIgnoreCase(Constantes.DEPOSITO)){
            Double saldoCta = cta.getSaldoInicial();
            Double valorDeposito = movimientos.getValor();
            Double sumaSaldoCta = (saldoCta + valorDeposito);
            cta.setSaldoInicial(sumaSaldoCta);
        } else if (tipoMovimiento.equalsIgnoreCase(Constantes.RETIRO)) {
            if(movimientos.getValor() > cta.getSaldoInicial()){
                throw new InformacionException(Constantes.INFO_SALDO);
            }
            Double saldoCta = cta.getSaldoInicial();
            Double valorRetiro = movimientos.getValor();
            Double restaSaldoCta = saldoCta - valorRetiro;
            cta.setSaldoInicial(restaSaldoCta);
        }

        return cta;
    }



    @Override
    public List<MovimientoDto> listMovimientos() {
        return mapper.mapperListMovimientos(repository.findAll());
    }

    @Override
    public List<MovimientoDto> reportes(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long cliente) {
        Cuenta cta = ctaRepository.findById(cliente)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta Id no regitra en el sistema"));
        List<MovimientoDto>reporteFinal = new ArrayList<>();
        List<MovimientoDto>reporte = mapper.mapperListMovimientos(repository.rangosFechas(fechaInicio,fechaFin,cliente));
          for( MovimientoDto report: reporte ) {
                report.setCliente(cta.getNombreClientId());
                report.setNumeroCuenta(cta.getNumeroCuenta());
                report.setTipoCuenta(cta.getTipoCuenta());
                report.setSaldoInicial(cta.getSaldoInicial());
                report.setEstado(cta.getEstado());
                reporteFinal.add(report);
          }
          return reporteFinal;
    }

    @Override
    public MovimientoDto actualizarMovimientos(Long id, MovimientoDto movimientoDto) {
        Movimientos movimientos = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(""));
        mapper.actualizarMovimientoMapper(movimientos,movimientoDto);
        repository.save(movimientos);
       return mapper.mapperMovimientos(movimientos);
    }
}
