package com.kamilly.meson.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RestauranteReqDTO {
    private String cnpj;
    @JsonProperty("razao_social")
    private String razaoSocial;
    @JsonProperty("nome_fantasia")
    private String nomeFantasia;
    @JsonProperty("cnae_fiscal")
    private String cnae;
    @JsonProperty("cnae_fiscal_descricao")
    private String descricaoCnae;
    private String telefone;
}
