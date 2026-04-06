package com.desafio_bradesco.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Proprietario {

    private UUID id;
    private String nome = "Bruno";
    private String CPF = "000.000.000-00";
    private Double saldo = 100.00;

}
