package com.devsu.cuenta.service;

import com.devsu.cuenta.dto.CuentaDto;
import com.devsu.cuenta.model.Cuenta;

import java.util.List;

public interface CuentaService {

    CuentaDto crearCuenta(Cuenta cuenta);

    List<CuentaDto>listarCuentas();

    CuentaDto actulizarDatosCuenta(CuentaDto cuenta,Long id);
}



