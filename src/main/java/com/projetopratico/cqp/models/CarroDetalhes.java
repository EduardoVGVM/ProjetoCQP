package com.projetopratico.cqp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "CarroDetalhes")
public class CarroDetalhes extends EntidadeBase {
    @Column(nullable = false, name = "URLDetalhes")
    private String URLDetalhes;
    @Column(nullable = false, name = "xpathNome")
    private String xpathNome;
    @Column(nullable = false, name = "xpathModelo")
    private String xpathModelo;
    @Column(nullable = false, name = "xpathCor")
    private String xpathCor;
    @Column(nullable = false, name = "xpathPreco")
    private String xpathPreco;
    @Column(nullable = false, name = "xpathURLimagem")
    private String xpathURLimagem;

    @OneToOne
    @JsonBackReference
    private Carro carro;
}
