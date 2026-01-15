package com.projetoJavaRestaurante.demo.dto.response;

public class ProdutoResponseDTO {
    private Long id;
    private Long restaurante;
    private Long categoriaProduto;
    private String nome;
    private String descricao;
    private Double preco;
    private String imagemUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Long restaurante) {
        this.restaurante = restaurante;
    }

    public Long getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(Long categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
