package com.projetopratico.cqp.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public abstract class EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(nullable = false)
    private Date DataCriacao;
    @Column(nullable = false)
    private Date DataAtualizacao;

    public EntidadeBase() {
    }

    public EntidadeBase(int id, Date dataCriacao, Date dataAtualizacao) {
        Id = id;
        DataCriacao = dataCriacao;
        DataAtualizacao = dataAtualizacao;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getDataCriacao() {
        return DataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        DataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return DataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        DataAtualizacao = dataAtualizacao;
    }
}