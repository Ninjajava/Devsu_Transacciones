package com.devsu.cuenta.service;

import com.devsu.cuenta.controller.InformacionException;
import com.devsu.cuenta.dto.CuentaDto;
import com.devsu.cuenta.mapper.MapperDatos;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.repository.CuentaRepository;
import com.devsu.cuenta.response.ResponseClient;
import com.devsu.cuenta.utils.Constantes;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class CuentaServiceImp implements CuentaService{


    private final CuentaRepository repository;

    private final MapperDatos mapperDatos;

    private final WebClient client;

    public CuentaServiceImp(CuentaRepository repository, MapperDatos mapperDatos, WebClient client) {
        this.repository = repository;
        this.mapperDatos = mapperDatos;
        this.client = client;
    }

    @Override
    public CuentaDto crearCuenta(Cuenta cuenta) {

        ResponseClient cliente = client.get()
                .uri("/{id}",cuenta.getClienteId())
                .retrieve()
                .bodyToMono(ResponseClient.class)
                .block();

        if (cliente == null) {
            throw new InformacionException("El cliente con id " + cuenta.getClienteId() + " no existe");
        }
        cuenta.setNombreClientId(cliente.getNombre());

        return mapperDatos.mapperDatos(repository.save(cuenta));
    }

    public ResponseClient getClientePorId(String clienteId) {
        int maxIntentos = 3;
        int intento = 0;
        ResponseClient client1 = new ResponseClient();

        while (intento < maxIntentos) {
            try {
                 client1 = client.get()
                        .uri("/{id}", clienteId)
                        .retrieve()
                        .bodyToMono(ResponseClient.class)
                        .block();
            } catch (Exception ex) {
                intento++;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new InformacionException("Error al consumir ms-clientes, intento " + intento + " de " + maxIntentos);
                }
            }
        }

        return client1;
    }


    @Override
    public List<CuentaDto> listarCuentas() {
        return mapperDatos.listaCuentaDto(repository.findAll());

    }

    @Override
    public CuentaDto actulizarDatosCuenta(CuentaDto cuenta, Long id) {
        Cuenta cta = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Constantes.NO_ENCONTRADO));
        mapperDatos.actualizarCuentaMapper(cta,cuenta);
        repository.save(cta);
        return mapperDatos.mapperDatos(cta);
    }
}
