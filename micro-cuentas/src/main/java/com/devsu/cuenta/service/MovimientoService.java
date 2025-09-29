package com.devsu.cuenta.service;

import com.devsu.cuenta.dto.MovimientoDto;
import com.devsu.cuenta.model.Movimientos;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoService {


    MovimientoDto registraMovimiento(Movimientos movimientos);

    List<MovimientoDto> listMovimientos();

    List<MovimientoDto>reportes(LocalDateTime fechaInicio,LocalDateTime fechaFin,Long cliente);

    MovimientoDto actualizarMovimientos(Long id, MovimientoDto movimientoDto);


}
