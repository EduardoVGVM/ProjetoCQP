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
    private String nome;

    public Montadora toEntity() {
        return Montadora.builder()
                .nome(this.nome)
                .dataCriacao(LocalDate.now())
                .dataAtualizacao(LocalDate.now())
                .build();
    }

    public Montadora toEntityUpdate(Montadora montadora) {
        return Montadora.builder()
                .id(montadora.getId())
                .nome(this.nome)
                .dataCriacao(montadora.getDataCriacao())
                .dataAtualizacao(LocalDate.now())
                .build();
    }
}
