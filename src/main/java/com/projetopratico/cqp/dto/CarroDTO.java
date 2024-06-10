package com.projetopratico.cqp.dto;
import java.text.DecimalFormat;
import java.time.LocalDate;

import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.models.Montadora;

import jakarta.validation.constraints.DecimalMin;
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
    private String Nome;
    @NotBlank
    @Size(min = 2, max = 100)
    private String Modelo;
    @NotBlank
    @Size(min = 2, max = 100)
    private String Cor;
    @NotNull
    @DecimalMin(value = "0.0")
    private DecimalFormat Preco;
    @NotBlank
    @Size(min = 2, max = 100)
    private String URLimagem;
    @NotBlank
    private int Montadora_Id;


    public Carro toEntity(Montadora montadora) {
        return Carro.builder()
                .Nome(this.Nome)
                .Modelo(this.Modelo)
                .Cor(this.Cor)
                .Preco(this.Preco)
                .URLimagem(this.URLimagem)
                .DataCriacao(LocalDate.now())
                .DataAtualizacao(LocalDate.now())
                .montadora(montadora)
                .build();
    }

    public Carro toEntityUpdate(Carro carro, Montadora montadora) {
        return Carro.builder()
                .Id(carro.getId())
                .Nome(this.Nome)
                .Modelo(this.Modelo)
                .Cor(this.Cor)
                .Preco(this.Preco)
                .URLimagem(this.URLimagem)
                .DataCriacao(carro.getDataCriacao())
                .DataAtualizacao(LocalDate.now())
                .montadora(montadora)
                .build();
    }
}
