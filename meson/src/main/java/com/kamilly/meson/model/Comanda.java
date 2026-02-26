package com.kamilly.meson.model;

import com.kamilly.meson.model.enums.StatusComanda;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "valor_comanda", nullable = false)
    private BigDecimal valor = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;

    @UpdateTimestamp
    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

//    @OneToMany(mappedBy = "comanda")
//    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "comanda")
    private Set<Pedido> pedidos = new HashSet<>();

    public BigDecimal calcularValor() {
        return pedidos.stream().map(Pedido::calcularValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
