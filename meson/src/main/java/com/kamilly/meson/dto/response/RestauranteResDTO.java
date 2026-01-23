package com.kamilly.meson.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RestauranteResDTO {
    private String id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnae;
    private String descricaoCnae;

}
