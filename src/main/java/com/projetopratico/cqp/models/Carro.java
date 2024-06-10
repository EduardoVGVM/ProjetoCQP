package com.projetopratico.cqp.models;

import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Table(name = "Carro")
public class Carro extends EntidadeBase {

    @Column(nullable = false, name = "Nome")
    private String Nome;
    @Column(nullable = false, name = "Modelo")
    private String Modelo;
    @Column(nullable = false, name = "Cor")
    private String Cor;
    @Column(nullable = false, name = "Preco")
    private DecimalFormat Preco;
    @Column(nullable = false, name = "URLimagem")
    private String URLimagem;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonBackReference
    private Montadora montadora;

    @OneToOne(mappedBy = "carro")
    @JsonBackReference
    private CarroDetalhes carroDetalhes;
}
