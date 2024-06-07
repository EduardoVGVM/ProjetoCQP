package com.projetopratico.cqp.models;

import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
    @JsonBackReference
    private Montadora montadora;

    @OneToOne(mappedBy = "CarroDetalhes")
    @JsonBackReference
    private CarroDetalhes carroDetalhes;
}
