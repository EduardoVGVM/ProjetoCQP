package com.projetopratico.cqp.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "Montadora")
public class Montadora extends EntidadeBase{
    @Column(nullable = false, name = "Nome")
    private String Nome;

    @OneToMany(mappedBy = "Carro")
    @JsonBackReference
    private List<Carro> carros;
}
