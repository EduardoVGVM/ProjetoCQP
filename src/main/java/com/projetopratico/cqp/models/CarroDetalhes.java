package com.projetopratico.cqp.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

public class CarroDetalhes extends EntidadeBase {
    @Column(nullable = false)
    private String URLDetalhes;
    @Column(nullable = false)
    private String xpathNome;
    @Column(nullable = false)
    private String xpathModelo;
    @Column(nullable = false)
    private String xpathCor;
    @Column(nullable = false)
    private String xpathPreco;
    @Column(nullable = false)
    private String xpathURLimagem;

    @OneToOne
    @JsonBackReference
    private Carro carro;

    public CarroDetalhes() {
    }

    public CarroDetalhes(int id, Date dataCriacao, Date dataAtualizacao, String uRLDetalhes, String xpathNome,
            String xpathModelo, String xpathCor, String xpathPreco, String xpathURLimagem, Carro carro) {
        super(id, dataCriacao, dataAtualizacao);
        URLDetalhes = uRLDetalhes;
        this.xpathNome = xpathNome;
        this.xpathModelo = xpathModelo;
        this.xpathCor = xpathCor;
        this.xpathPreco = xpathPreco;
        this.xpathURLimagem = xpathURLimagem;
        this.carro = carro;
    }

    public String getURLDetalhes() {
        return URLDetalhes;
    }

    public void setURLDetalhes(String uRLDetalhes) {
        URLDetalhes = uRLDetalhes;
    }

    public String getXpathNome() {
        return xpathNome;
    }

    public void setXpathNome(String xpathNome) {
        this.xpathNome = xpathNome;
    }

    public String getXpathModelo() {
        return xpathModelo;
    }

    public void setXpathModelo(String xpathModelo) {
        this.xpathModelo = xpathModelo;
    }

    public String getXpathCor() {
        return xpathCor;
    }

    public void setXpathCor(String xpathCor) {
        this.xpathCor = xpathCor;
    }

    public String getXpathPreco() {
        return xpathPreco;
    }

    public void setXpathPreco(String xpathPreco) {
        this.xpathPreco = xpathPreco;
    }

    public String getXpathURLimagem() {
        return xpathURLimagem;
    }

    public void setXpathURLimagem(String xpathURLimagem) {
        this.xpathURLimagem = xpathURLimagem;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

}
