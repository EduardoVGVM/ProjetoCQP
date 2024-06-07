package com.projetopratico.cqp.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(nullable = false, name = "DataCriacao")
    private LocalDate DataCriacao;
    @Column(nullable = false, name = "DataAtualizacao")
    private LocalDate DataAtualizacao;

}