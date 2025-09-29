package com.devsu.service;
import com.devsu.dto.ClienteDto;
import com.devsu.mapper.MapperEntity;
import com.devsu.model.Cliente;
import com.devsu.repository.PersonaRepository;
import com.devsu.utils.Constantes;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PersonaServiceImp implements PersonaService{

    final PersonaRepository repository;
    final MapperEntity mapper;

    @Override
    public ClienteDto registroPersona(Cliente cliente) {
        return mapper.mapperDatos(repository.save(cliente));
    }

    @Override
    public List<ClienteDto> listarClientes() {
        return mapper.toClienteDatoList(repository.findAll());
    }

    @Override
    public ClienteDto actualizar(Long id,ClienteDto cliente) {
        Cliente cli = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Constantes.NO_ENCONTRADO));
        mapper.actualizarClienteMapper(cli,cliente);
        repository.save(cli);
        return mapper.mapperDatos(cli);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente cli = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Constantes.NO_ENCONTRADO));
        repository.delete(cli);
    }

    @Override
    public ClienteDto buscarPorId(Long id) {
        Cliente cl = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Constantes.NO_ENCONTRADO));
        return mapper.mapperDatos(cl);
    }


}
