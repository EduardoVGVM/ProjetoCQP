package com.projetopratico.cqp.dto;

import java.time.LocalDate;

import com.projetopratico.cqp.models.CarroDetalhes;

import jakarta.validation.constraints.NotBlank;
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
    private String urlDetalhes;
    @NotBlank
    private String xpathNome;
    @NotBlank
    private String xpathModelo;
    @NotBlank
    private String xpathCor;
    @NotBlank
    private String xpathPreco;
    @NotBlank
    private String xpathUrlImagem;

    public CarroDetalhes toEntity() {
        return CarroDetalhes.builder()
                .urlDetalhes(urlDetalhes)
                .xpathNome(this.xpathNome)
                .xpathModelo(this.xpathModelo)
                .xpathCor(this.xpathCor)
                .xpathPreco(this.xpathPreco)
                .xpathUrlImagem(xpathUrlImagem)
                .dataCriacao(LocalDate.now())
                .dataAtualizacao(LocalDate.now())
                .build();
    }

    public CarroDetalhes toEntityUpdate(CarroDetalhes carroDetalhes) {
        return CarroDetalhes.builder()
                .id(carroDetalhes.getId())
                .urlDetalhes(urlDetalhes)
                .xpathNome(this.xpathNome)
                .xpathModelo(this.xpathModelo)
                .xpathCor(this.xpathCor)
                .xpathPreco(this.xpathPreco)
                .xpathUrlImagem(xpathUrlImagem)
                .dataCriacao(carroDetalhes.getDataCriacao())
                .dataAtualizacao(LocalDate.now())
                .build();
    }
}
