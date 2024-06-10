package com.projetopratico.cqp.dto;

import java.time.LocalDate;

import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.models.CarroDetalhes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarroDetalhesDTO {
    @NotBlank
    @Size(min = 2, max = 255)
    private String URLDetalhes;
    @NotBlank
    @Size(min = 2, max = 255)
    private String xpathNome;
    @NotBlank
    @Size(min = 2, max = 255)
    private String xpathModelo;
    @NotBlank
    @Size(min = 2, max = 255)
    private String xpathCor;
    @NotBlank
    @Size(min = 2, max = 255)
    private String xpathPreco;
    @NotBlank
    @Size(min = 2, max = 255)
    private String xpathURLimagem;
    @NotBlank
    private int Carro_Id;

    public CarroDetalhes toEntity(Carro carro) {
        return CarroDetalhes.builder()
                .URLDetalhes(this.URLDetalhes)
                .xpathNome(this.xpathNome)
                .xpathModelo(this.xpathModelo)
                .xpathCor(this.xpathCor)
                .xpathPreco(this.xpathPreco)
                .xpathURLimagem(this.xpathURLimagem)
                .DataCriacao(LocalDate.now())
                .DataAtualizacao(LocalDate.now())
                .carro(carro)
                .build();
    }

    public CarroDetalhes toEntityUpdate(CarroDetalhes carroDetalhes, Carro carro) {
        return CarroDetalhes.builder()
                .Id(carroDetalhes.getId())
                .URLDetalhes(this.URLDetalhes)
                .xpathNome(this.xpathNome)
                .xpathModelo(this.xpathModelo)
                .xpathCor(this.xpathCor)
                .xpathPreco(this.xpathPreco)
                .xpathURLimagem(this.xpathURLimagem)
                .DataCriacao(carroDetalhes.getDataCriacao())
                .DataAtualizacao(LocalDate.now())
                .carro(carro)
                .build();
    }
}
