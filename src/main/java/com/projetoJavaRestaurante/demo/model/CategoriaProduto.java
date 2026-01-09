package com.projetoJavaRestaurante.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "categoriaProduto",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_categria_restaurante_nome",
                        columnNames = {"id_restaurante", "nome_categoria"}
                )
        }
)
public class CategoriaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private int id;

    @Column(name = "nome_categoria")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;
}
