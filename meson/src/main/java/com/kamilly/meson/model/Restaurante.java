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

    @Column(unique = true)
    private String cnpj;

    @Column(name = "nome_restaurante")
    private String nomeFantasia;

    @Column(name = "telefone_restaurante")
    private String telefone;

    private String cnae;

    @Column(name = "cnae_descricao")
    private String descricaoCnae;

    @Column(name = "razao_social")
    private String razaoSocial;

    private Boolean ativo;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
}
