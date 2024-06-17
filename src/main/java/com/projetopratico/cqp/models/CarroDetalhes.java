package com.projetopratico.cqp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class CarroDetalhes extends EntidadeBase {
    @Column(nullable = false, name = "urlDetalhes")
    private String urlDetalhes;
    @Column(nullable = false, name = "xpathNome")
    private String xpathNome;
    @Column(nullable = false, name = "xpathModelo")
    private String xpathModelo;
    @Column(nullable = false, name = "xpathCor")
    private String xpathCor;
    @Column(nullable = false, name = "xpathPreco")
    private String xpathPreco;
    @Column(nullable = false, name = "xpathUrlImagem")
    private String xpathUrlImagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carro_id", nullable = false)
    @JsonBackReference
    private Carro carro;
}
