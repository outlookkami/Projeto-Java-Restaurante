package com.kamilly.meson.model;

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
@Table(name = "comandas")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    private String status;

    @CreationTimestamp
    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;

    @UpdateTimestamp
    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    //@OneToMany(mappedBy = "comanda")
    //@JsonIgnore
    //private List<Produto> produtos;
}
