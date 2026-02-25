package com.kamilly.meson.model;

import com.kamilly.meson.model.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @Column(name = "numero_dia")
    private Integer numeroDia;

    private LocalDate dataReferencia;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

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

    @Column(name = "valor_pedido")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda comanda;

    @OneToMany(mappedBy = "pedido",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<ItemPedido> itens;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario garcom;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;
}
