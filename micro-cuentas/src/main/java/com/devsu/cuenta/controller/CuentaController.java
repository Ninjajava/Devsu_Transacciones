package com.devsu.cuenta.controller;

import com.devsu.cuenta.dto.CuentaDto;
import com.devsu.cuenta.model.Cuenta;
import com.devsu.cuenta.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cuentas")
public class CuentaController {


      private final CuentaService  service;

    public CuentaController(CuentaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CuentaDto>crearCuenta(@RequestBody Cuenta cuenta){
        return ResponseEntity.ok().body(service.crearCuenta(cuenta));
    }

    @GetMapping
    public ResponseEntity<List<CuentaDto>>listaCuentas(){
        return ResponseEntity.ok().body(service.listarCuentas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDto>actualizarCuenta(@PathVariable Long id,
                                                     @RequestBody CuentaDto cuentaDto){
        return ResponseEntity.ok().body(service.actulizarDatosCuenta(cuentaDto,id));
    }






}
