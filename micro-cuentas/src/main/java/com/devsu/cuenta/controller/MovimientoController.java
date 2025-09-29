package com.devsu.cuenta.controller;

import com.devsu.cuenta.dto.MovimientoDto;
import com.devsu.cuenta.model.Movimientos;
import com.devsu.cuenta.service.MovimientoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {


    private final MovimientoService service;

    public MovimientoController(MovimientoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MovimientoDto>registrarMovimiento(@RequestBody Movimientos movimientos){
        MovimientoDto dto= service.registraMovimiento(movimientos);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDto>>listMovimientos(){
        List<MovimientoDto>list = service.listMovimientos();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping
    public ResponseEntity<MovimientoDto>actualizarMovimiento(@PathVariable Long id,
                                                             @RequestBody MovimientoDto movimiento){
        MovimientoDto movimientoDto = service.actualizarMovimientos(id,movimiento);
        return ResponseEntity.ok().body(movimientoDto);
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<MovimientoDto>>reportesMovimientos(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime fechaFin,
            @RequestParam("cliente")Long cliente){

        return ResponseEntity.ok().body(service.reportes(fechaInicio,fechaFin,cliente));

    }



}
