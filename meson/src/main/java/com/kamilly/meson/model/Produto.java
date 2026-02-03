package com.kamilly.meson.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(
        name = "produtos",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_produto_restaurante_nome",
                        columnNames = {"id_restaurante","nome_produto"}
                )
        }
)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;

    @Column(name = "nome_produto")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_produto")
    private CategoriaProduto categoria;
//
//    @Column(name = "categoria_produto")
//    private String categoria;

    @Column(name = "descricao_produto")
    private String descricao;

    @Column(name = "preco_produto")
    private BigDecimal preco;

    @Column(name = "imagem_produto")
    private String imagemUrl;

    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaProduto categoriaProduto;
}
