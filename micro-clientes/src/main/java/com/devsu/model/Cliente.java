package com.devsu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;

    private String estado;

    private String contrasena;

}
