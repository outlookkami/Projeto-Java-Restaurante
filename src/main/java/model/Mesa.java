package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private int id;
    
    @Column(name = "numero_mesa")
    private short numero;

    @Column(name = "posicao_x")
    private int posicaoX;

    @Column(name = "posicao_y")
    private int posicaoY;

    private String status;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;
}
