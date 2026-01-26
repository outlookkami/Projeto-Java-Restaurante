package com.kamilly.meson.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "restaurante")
public class Restaurante{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante")
    private int id;

    @Column(unique = true, nullable = false,  length = 14)
    private String cnpj;

    @Column(name = "nome_restaurante", length = 100)
    private String nomeFantasia;

    @Column(name = "telefone_restaurante", length = 14)
    private String telefone;

    @Column(length = 7)
    private String cnae;

    @Column(name = "cnae_descricao", length = 180)
    private String descricaoCnae;

    @Column(name = "razao_social", length = 100)
    private String razaoSocial;

    private Boolean ativo;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
}
