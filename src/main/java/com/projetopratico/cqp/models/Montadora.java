package com.projetopratico.cqp.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Montadora extends EntidadeBase{
    @Column(nullable = false)
    private String Nome;

    @OneToMany
    @JsonBackReference
    private List<Carro> carros;

    public Montadora() {
    }

    public Montadora(int id, Date dataCriacao, Date dataAtualizacao, String nome, List<Carro> carros) {
        super(id, dataCriacao, dataAtualizacao);
        Nome = nome;
        this.carros = carros;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
}
