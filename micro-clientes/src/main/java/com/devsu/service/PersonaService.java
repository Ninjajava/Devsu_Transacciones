package com.devsu.service;

import com.devsu.dto.ClienteDto;
import com.devsu.model.Cliente;
import com.devsu.model.Persona;

import java.util.List;

public interface PersonaService {

    ClienteDto registroPersona(Cliente cliente);

    List<ClienteDto> listarClientes();

    ClienteDto actualizar(Long id,ClienteDto cliente);

    void eliminarCliente(Long id);

    ClienteDto buscarPorId(Long id);
}
