package com.projetoJavaRestaurante.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private String nome;

    private Boolean ativo;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
}
