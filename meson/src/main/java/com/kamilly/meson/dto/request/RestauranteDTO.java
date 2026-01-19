package com.kamilly.meson.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RestauranteDTO {
    private String cnpj;
    @JsonProperty("razao_social")
    private String razaoSocial;
    @JsonProperty("nome_fantasia")
    private String nomeFantasia;
    @JsonProperty("cnae_fiscal")
    private String cnae;
    @JsonProperty("cnae_fiscal_descricao")
    private String descricaoCnae;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getDescricaoCnae() {
        return descricaoCnae;
    }

    public void setDescricaoCnae(String descricaoCnae) {
        this.descricaoCnae = descricaoCnae;
    }
}
