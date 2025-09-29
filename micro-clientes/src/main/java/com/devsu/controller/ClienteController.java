package com.devsu.controller;
import com.devsu.dto.ClienteDto;
import com.devsu.model.Cliente;
import com.devsu.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    public final PersonaService service;

    @PostMapping
    public ResponseEntity<ClienteDto> registro(@RequestBody Cliente cliente){
       return ResponseEntity.ok().body(service.registroPersona(cliente));
    }

    @GetMapping
    public  ResponseEntity<List<ClienteDto>>listarClientes(){
        return ResponseEntity.ok().body(service.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto>clientePorId(@PathVariable Long id){
        ClienteDto dto = service.buscarPorId(id);
        return  ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizarCliente(@PathVariable Long id,
            @RequestBody ClienteDto cliente) {
        ClienteDto actualizada = service.actualizar(id,cliente );
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminarCliente(@PathVariable Long id){
        service.eliminarCliente(id);
        return ResponseEntity.ok().build();
    }


}
