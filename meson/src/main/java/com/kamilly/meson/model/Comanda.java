package com.kamilly.meson.model;

import com.kamilly.meson.model.enums.StatusComanda;
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
@Table(name = "comandas")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;

    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Enumerated(EnumType.STRING)
    private StatusComanda status;

    @Column(name = "valor_comanda")
    private BigDecimal valor;

    @CreationTimestamp
    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;

    @UpdateTimestamp
    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    @OneToMany(mappedBy = "comanda")
    private List<Pedido> pedidos;
}
