package com.kamilly.meson.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RestauranteAnaliseResDTO {
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnae;
    private String descricaoCnae;
    private String telefone;
}
