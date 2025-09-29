package com.devsu.cuenta.mapper;

import com.devsu.cuenta.dto.CuentaDto;
import com.devsu.cuenta.dto.MovimientoDto;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.model.Movimientos;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperDatos {


    public  CuentaDto mapperDatos(Cuenta cuenta){
        CuentaDto ctaDto = new CuentaDto();
        ctaDto.setEstado(cuenta.getEstado());
        ctaDto.setTipoCuenta(cuenta.getTipoCuenta());
        ctaDto.setNumeroCuenta(cuenta.getNumeroCuenta());
        ctaDto.setSaldoInicial(cuenta.getSaldoInicial());
        return ctaDto;
    }

    public MovimientoDto mapperMovimientos(Movimientos movimientos){
        MovimientoDto dto = new MovimientoDto();
        dto.setFecha(movimientos.getFecha());
        dto.setTipoMovimiento(movimientos.getTipoMovimiento());
        dto.setValor(movimientos.getValor());
        dto.setSaldo(movimientos.getSaldo());
        return dto;
    }

    public MovimientoDto mapperReporteMovimientos(Movimientos movimientos,Cuenta cuenta){
        MovimientoDto dto = new MovimientoDto();
        dto.setFecha(movimientos.getFecha());
        dto.setCliente(cuenta.getNombreClientId());
        dto.setTipoMovimiento(movimientos.getTipoMovimiento());
        dto.setValor(movimientos.getValor());
        dto.setSaldo(movimientos.getSaldo());
        return dto;
    }

    public List<CuentaDto> listaCuentaDto(List<Cuenta> cuentas) {
        return cuentas.stream()
                .map(this::mapperDatos)
                .collect(Collectors.toList());
    }

    public List<MovimientoDto>mapperListMovimientos(List<Movimientos>movimientos){
        return movimientos.stream().map(this::mapperMovimientos)
                .collect(Collectors.toList());
    }



    public Cuenta actualizarCuentaMapper(Cuenta cuenta,CuentaDto cuentaDto){
        cuenta.setId(cuenta.getId());
        cuenta.setEstado(cuentaDto.getEstado());
        cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
        cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
        cuenta.setTipoCuenta(cuentaDto.getTipoCuenta());
        return cuenta;
    }

    public Movimientos actualizarMovimientoMapper(Movimientos movimientos, MovimientoDto dto){
        movimientos.setId(movimientos.getId());
        movimientos.setTipoMovimiento(dto.getTipoMovimiento());
        movimientos.setValor(dto.getValor());
        movimientos.setSaldo(dto.getSaldo());
        movimientos.setFecha(dto.getFecha());
        return movimientos;
    }
}
