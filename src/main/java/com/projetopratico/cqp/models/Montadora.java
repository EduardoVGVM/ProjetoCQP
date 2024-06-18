package com.projetopratico.cqp.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Montadora extends EntidadeBase {
    @Column(nullable = false, name = "nome")
    private String nome;

    @OneToMany(mappedBy = "montadora", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Carro> carros;
}
