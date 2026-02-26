package com.kamilly.meson.model;

import com.kamilly.meson.model.enums.StatusMesa;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(
        name = "mesas",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_mesas_restaurante_numero",
                        columnNames = {"id_restaurante", "numero_mesa"}
                )
        }
)
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")
    private Long id;
    
    @Column(name = "numero_mesa")
    private short numero;

    @Column(name = "capacidade_mesa")
    private short capacidade;

    @Column(name = "posicao_x")
    private int posicaoX;

    @Column(name = "posicao_y")
    private int posicaoY;

    @Enumerated(EnumType.STRING)
    private StatusMesa status = StatusMesa.DISPONIVEL;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;
}
