package com.projetopratico.cqp.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.projetopratico.cqp.dto.CarroDetalhesDTO;
import com.projetopratico.cqp.models.CarroDetalhes;
import com.projetopratico.cqp.repositories.CarroDetalhesRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarroDetalhesService {
    private final CarroDetalhesRepository carroDetalhesRepository;
    private static final Logger logger = LoggerFactory.getLogger(CarroDetalhesService.class);

    @Async
    public CompletableFuture<CarroDetalhes> create(CarroDetalhesDTO carroDetalhesDTO) {
        try {
            CarroDetalhes carroDetalhes = carroDetalhesDTO.toEntity();
            return CompletableFuture.completedFuture(this.carroDetalhesRepository.save(carroDetalhes));
        } catch (Exception e) {
            logger.error("Erro ao criar os Detalhes", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<List<CarroDetalhes>> listAll() {
        try {
            List<CarroDetalhes> carroDetalhesList = carroDetalhesRepository.findAll();
            return CompletableFuture.completedFuture(carroDetalhesList.stream().collect(Collectors.toList()));
        } catch (Exception e) {
            logger.error("Erro ao listar os Detalhes", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<CarroDetalhes> getById(int id) {
        try {
            return CompletableFuture.completedFuture(this.carroDetalhesRepository.findById(id).orElse(null));
        } catch (Exception e) {
            logger.error("Erro ao listar um Detalhe por Id", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<CarroDetalhes> update(int id, @Valid CarroDetalhesDTO carroDetalhesDTO) {
        try {
            CarroDetalhes carroDetalhes = carroDetalhesRepository.findById(id).orElse(null);
            if (carroDetalhes != null) {
                CarroDetalhes updateCarroDetalhes = carroDetalhesDTO.toEntityUpdate(carroDetalhes);
                return CompletableFuture.completedFuture(this.carroDetalhesRepository.save(updateCarroDetalhes));
            }
            return null;
        } catch (Exception e) {
            logger.error("Erro ao atualizar os Detalhes", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Boolean> delete(int id) {
        try {
            CarroDetalhes carroDetalhes = this.carroDetalhesRepository.findById(id).orElse(null);
            if (carroDetalhes != null) {
                this.carroDetalhesRepository.delete(carroDetalhes);
                return CompletableFuture.completedFuture(true);
            }
            return CompletableFuture.completedFuture(false);
        } catch (Exception e) {
            logger.error("Erro ao deletar os Detalhes", e);
            return CompletableFuture.failedFuture(e);
        }
    }
}
