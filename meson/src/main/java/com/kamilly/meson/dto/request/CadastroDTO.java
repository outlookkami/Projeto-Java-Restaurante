package com.kamilly.meson.dto.request;

import lombok.Data;

@Data
public class CadastroDTO {
    private String cnpj;
//    private String nomeRestaurante;
//    private String razaoSocial;
//    private String cnae;
//    private String cnaeDescricao;
    private String telefone;

    private String nomeAdmin;
    private String emailAdmin;
    private String senhaAdmin;
}
