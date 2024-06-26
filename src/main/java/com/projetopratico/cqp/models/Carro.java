package com.projetopratico.cqp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Carro extends EntidadeBase {

    @Column(nullable = false, name = "nome")
    private String nome;
    @Column(nullable = false, name = "modelo")
    private String modelo;
    @Column(nullable = false, name = "cor")
    private String cor;
    @Column(nullable = false, name = "preco")
    private double preco;
    @Column(nullable = false, name = "urlImagem")
    private String urlImagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "montadora_id", nullable = false)
    @JsonBackReference
    private Montadora montadora;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carroDetalhes_id", nullable = false)
    @JsonBackReference
    private CarroDetalhes carroDetalhes;
}
