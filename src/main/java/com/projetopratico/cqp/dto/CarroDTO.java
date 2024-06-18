package com.projetopratico.cqp.dto;

import java.time.LocalDate;

import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.models.CarroDetalhes;
import com.projetopratico.cqp.models.Montadora;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String nome;
    @NotBlank
    private String modelo;
    @NotBlank
    private String cor;
    @NotNull
    private double preco;
    @NotBlank
    private String urlImagem;
    @NotNull
    @Min(value = 1)
    private int montadora_id;
    @NotNull
    @Min(value = 1)
    private int carroDetalhes_id;

    public Carro toEntity(Montadora montadora, CarroDetalhes carroDetalhes) {
        return Carro.builder()
                .nome(this.nome)
                .modelo(this.modelo)
                .cor(this.cor)
                .preco(this.preco)
                .urlImagem(this.urlImagem)
                .dataCriacao(LocalDate.now())
                .dataAtualizacao(LocalDate.now())
                .montadora(montadora)
                .carroDetalhes(carroDetalhes)
                .build();
    }

    public Carro toEntityUpdate(Carro carro, Montadora montadora, CarroDetalhes carroDetalhes) {
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
                .carroDetalhes(carroDetalhes)
                .build();
    }
}
