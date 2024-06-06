package com.projetopratico.cqp.models;

import java.text.DecimalFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Carro extends EntidadeBase {

    @Column(nullable = false)
    private String Nome;
    @Column(nullable = false)
    private String Modelo;
    @Column(nullable = false)
    private String Cor;
    @Column(nullable = false)
    private DecimalFormat Preco;
    @Column(nullable = false)
    private String URLimagem;

    @ManyToOne
    @JsonBackReference
    private Montadora montadora;

    public Carro() {
    }

    public Carro(int id, Date dataCriacao, Date dataAtualizacao, String nome, String modelo, String cor,
            DecimalFormat preco, String uRLimagem, Montadora montadora) {
        super(id, dataCriacao, dataAtualizacao);
        Nome = nome;
        Modelo = modelo;
        Cor = cor;
        Preco = preco;
        URLimagem = uRLimagem;
        this.montadora = montadora;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getCor() {
        return Cor;
    }

    public void setCor(String cor) {
        Cor = cor;
    }

    public DecimalFormat getPreco() {
        return Preco;
    }

    public void setPreco(DecimalFormat preco) {
        Preco = preco;
    }

    public String getURLimagem() {
        return URLimagem;
    }

    public void setURLimagem(String uRLimagem) {
        URLimagem = uRLimagem;
    }

    public Montadora getMontadora() {
        return montadora;
    }

    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }
}
