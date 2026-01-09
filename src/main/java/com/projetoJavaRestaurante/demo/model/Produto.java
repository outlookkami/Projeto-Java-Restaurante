package com.projetoJavaRestaurante.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private long id;

    @Column(name = "nome_produto")
    private String nome;

    @Column(name = "descricao_produto")
    private String descricao;

    @Column(name = "preco_produto")
    private BigDecimal preco;

    private Boolean ativo;

    @Column(name = "imagem_produto")
    private String imagemUrl;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaProduto categoriaProduto;
}
