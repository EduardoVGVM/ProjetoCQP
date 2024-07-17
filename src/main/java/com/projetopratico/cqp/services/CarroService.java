package com.projetopratico.cqp.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.projetopratico.cqp.dto.CarroDTO;
import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.models.CarroDetalhes;
import com.projetopratico.cqp.models.Montadora;
import com.projetopratico.cqp.repositories.CarroDetalhesRepository;
import com.projetopratico.cqp.repositories.CarroRepository;
import com.projetopratico.cqp.repositories.MontadoraRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarroService {
    private final CarroRepository carroRepository;
    private final MontadoraRepository montadoraRepository;
    private final CarroDetalhesRepository carroDetalhesRepository;
    private static final Logger logger = LoggerFactory.getLogger(CarroService.class);

    @Async
    public CompletableFuture<Carro> create(CarroDTO carroDTO) {
        try {
            Montadora montadora = this.montadoraRepository.findById(carroDTO.getMontadora_id()).orElse(null);
            CarroDetalhes carroDetalhes = this.carroDetalhesRepository.findById(carroDTO.getCarroDetalhes_id())
                    .orElse(null);

            if (montadora != null && carroDetalhes != null) {
                Carro carro = carroDTO.toEntity(montadora, carroDetalhes);
                return CompletableFuture.completedFuture(this.carroRepository.save(carro));
            }
        } catch (Exception e) {
            logger.error("Erro ao criar o Carro", e);
            return CompletableFuture.failedFuture(e);
        }
        return null;
    }

    @Async
    public CompletableFuture<List<Carro>> listAll() {
        try {
            List<Carro> carros = carroRepository.findAll();
            return CompletableFuture.completedFuture(carros.stream().collect(Collectors.toList()));
        } catch (Exception e) {
            logger.error("Erro ao listar todos Carros", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Carro> getById(int id) {
        try {
            return CompletableFuture.completedFuture(this.carroRepository.findById(id).orElse(null));
        } catch (Exception e) {
            logger.error("Erro ao listar o Carro por Id", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Carro> update(int id, @Valid CarroDTO carroDTO) {
        try {
            Carro carro = this.carroRepository.findById(id).orElse(null);
            Montadora montadora = this.montadoraRepository.findById(carroDTO.getMontadora_id()).orElse(null);
            CarroDetalhes carroDetalhes = this.carroDetalhesRepository.findById(carroDTO.getCarroDetalhes_id())
                    .orElse(null);

            if (carro != null && montadora != null && carroDetalhes != null) {
                carro.setMontadora(montadora);
                carro.setCarroDetalhes(carroDetalhes);
                carro = carroDTO.toEntityUpdate(carro, montadora, carroDetalhes);
                return CompletableFuture.completedFuture(this.carroRepository.save(carro));
            }
            return null;
        } catch (Exception e) {
            logger.error("Erro ao atualizar o Carro", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Boolean> delete(int id) {
        try {
            Carro carro = this.carroRepository.findById(id).orElse(null);
            if (carro != null) {
                this.carroRepository.delete(carro);
                return CompletableFuture.completedFuture(true);
            }
            return CompletableFuture.completedFuture(false);
        } catch (Exception e) {
            logger.error("Erro ao deletar o Carro", e);
            return CompletableFuture.failedFuture(e);
        }
    }
}
