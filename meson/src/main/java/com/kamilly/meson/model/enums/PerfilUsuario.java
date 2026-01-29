package com.kamilly.meson.model.enums;

public enum PerfilUsuario {
    ADMIN_GERAL("Administrador Geral"),
    ADMIN("Administrador/Gerente"),
    GARCOM("Garçom"),
    COZINHA("Cozinheiro");

    private String descricao;
    PerfilUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
