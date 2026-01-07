package com.projetoJavaRestaurante.demo.model.enums;

public enum StatusUsuario {
    ATIVO(true),
    INATIVO(false);
    private boolean ativo;
    private StatusUsuario(boolean ativo) {}
}
