package com.projetopratico.cqp.dto;

import java.time.LocalDate;

import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.models.Montadora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarroDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;
    @NotBlank
    @Size(min = 2, max = 100)
    private String modelo;
    @NotBlank
    @Size(min = 2, max = 100)
    private String cor;
    @NotNull
    private double preco;
    @NotBlank
    @Size(min = 2, max = 100)
    private String urlImagem;
    @NotNull
    private int montadora_id;

    public Carro toEntity(Montadora montadora) {
        return Carro.builder()
                .nome(this.nome)
                .modelo(this.modelo)
                .cor(this.cor)
                .preco(this.preco)
                .urlImagem(this.urlImagem)
                .dataCriacao(LocalDate.now())
                .dataAtualizacao(LocalDate.now())
                .montadora(montadora)
                .build();
    }

    public Carro toEntityUpdate(Carro carro, Montadora montadora) {
        return Carro.builder()
                .id(carro.getId())
                .nome(this.nome)
                .modelo(this.modelo)
                .cor(this.cor)
                .preco(this.preco)
                .urlImagem(this.urlImagem)
                .dataCriacao(carro.getDataCriacao())
                .dataAtualizacao(LocalDate.now())
                .montadora(montadora)
                .build();
    }
}
