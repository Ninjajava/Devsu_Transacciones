package com.devsu.mapper;

import com.devsu.dto.ClienteDto;
import com.devsu.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

import java.util.stream.Collectors;

@Component
public class MapperEntity {


    public  ClienteDto mapperDatos(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setClientId(cliente.getClientId());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setEdad(cliente.getEdad());
        clienteDto.setGenero(cliente.getGenero());
        clienteDto.setDireccion(cliente.getDireccion());
        clienteDto.setTelefono(cliente.getTelefono());
        clienteDto.setEstado(cliente.getEstado());
        return clienteDto;
    }


    public  List<ClienteDto> toClienteDatoList(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::mapperDatos)
                .collect(Collectors.toList());
    }

    public Cliente actualizarClienteMapper(Cliente cli,ClienteDto cliente){
        cli.setClientId(cli.getClientId());
        cli.setNombre(cliente.getNombre());
        cli.setEdad(cliente.getEdad());
        cli.setEstado(cliente.getEstado());
        cli.setContrasena(cliente.getContrasena());
        cli.setDireccion(cliente.getDireccion());
        cli.setGenero(cliente.getGenero());
        cli.setTelefono(cliente.getTelefono());
        cli.setClientId(cli.getClientId());
        return cli;
    }



}
