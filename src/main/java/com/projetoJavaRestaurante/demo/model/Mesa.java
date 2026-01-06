package com.projetoJavaRestaurante.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mesas")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")
    private int id;
    
    @Column(name = "numero_mesa")
    private int numero;

    @Column(name = "posicao_x")
    private int posicaoX;

    @Column(name = "posicao_y")
    private int posicaoY;

    private String status;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;
}
