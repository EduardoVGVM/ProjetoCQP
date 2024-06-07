package com.projetopratico.cqp.dto;

import java.time.LocalDate;

import com.projetopratico.cqp.models.Montadora;

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
public class MontadoraDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String Nome;

    public Montadora toEntity() {
        return Montadora.builder()
                .Nome(this.Nome)
                .DataCriacao(LocalDate.now())
                .DataAtualizacao(LocalDate.now())
                .build();
    }

    public Montadora toEntityUpdate(Montadora montadora) {
        return Montadora.builder()
                .Id(montadora.getId())
                .Nome(this.Nome)
                .DataCriacao(montadora.getDataCriacao())
                .DataAtualizacao(LocalDate.now())
                .build();
    }
}
