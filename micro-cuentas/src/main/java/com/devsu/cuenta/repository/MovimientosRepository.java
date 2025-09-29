package com.devsu.cuenta.repository;

import com.devsu.cuenta.model.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {

    @Query("SELECT m FROM Movimientos m WHERE m.fecha BETWEEN :inicio AND :fin AND m.cuenta.id = :cuentaId")
    List<Movimientos>rangosFechas(@Param("inicio") LocalDateTime inicio,
                                  @Param("fin") LocalDateTime fin,
                                  @Param("cuentaId") Long id);
}
