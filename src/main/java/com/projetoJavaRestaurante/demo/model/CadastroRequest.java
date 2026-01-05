package com.projetoJavaRestaurante.demo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CadastroRequest {
    private final String nome;
    private final String sobrenome;
    private final String senha;
    private final String email;
}
