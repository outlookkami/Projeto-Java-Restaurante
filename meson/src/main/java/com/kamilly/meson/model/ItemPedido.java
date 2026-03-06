package com.kamilly.meson.model;

import com.kamilly.meson.model.enums.StatusItemPedido;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_pedido")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusItemPedido statusItem;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario",  nullable = false)
    private BigDecimal precoUnitario;

    private BigDecimal subtotal;

    @Column(name = "obs_item_pedido")
    private String obsItemPedido;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_preparo")
    private LocalDateTime dataPreparo;

    @Column(name = "data_pronto")
    private LocalDateTime dataPronto;

    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;

    public BigDecimal calcularSubtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
