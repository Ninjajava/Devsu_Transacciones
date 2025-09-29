package com.devsu.cuenta.repository;

import com.devsu.cuenta.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
}
