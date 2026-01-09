package com.projetoJavaRestaurante.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private long id;

    private String status;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_preparo")
    private LocalDateTime dataPreparo;

    @UpdateTimestamp
    @Column(name = "data_pronto")
    private LocalDateTime dataPronto;

    @UpdateTimestamp
    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;

    @Column(name = "obs_pedido")
    private String obsPedido;

    @ManyToOne
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda comanda;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario garcom;
}
