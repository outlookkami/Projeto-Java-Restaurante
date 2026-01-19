package com.kamilly.meson.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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
    private Long id;

    @Column(name = "nome_categoria")
    private String nome;

    private Boolean ativa;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;
}
