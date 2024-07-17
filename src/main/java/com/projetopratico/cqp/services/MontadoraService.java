package com.projetopratico.cqp.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.projetopratico.cqp.dto.MontadoraDTO;
import com.projetopratico.cqp.models.Montadora;
import com.projetopratico.cqp.repositories.MontadoraRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MontadoraService {
    private final MontadoraRepository montadoraRepository;
    private static final Logger logger = LoggerFactory.getLogger(MontadoraService.class);

    @Async
    public CompletableFuture<Montadora> create(MontadoraDTO montadoraDTO) {
        try {
            Montadora montadora = montadoraDTO.toEntity();
            return CompletableFuture.completedFuture(this.montadoraRepository.save(montadora));
        } catch (Exception e) {
            logger.error("Erro ao criar a montadora", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<List<Montadora>> listAll() {
        try {
            List<Montadora> montadoras = montadoraRepository.findAll();
            return CompletableFuture.completedFuture(montadoras.stream().collect(Collectors.toList()));
        } catch (Exception e) {
            logger.error("Erro ao listar as montadoras", e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Montadora> getById(int id) {
        try {
            return CompletableFuture.completedFuture(this.montadoraRepository.findById(id).orElse(null));
        } catch (Exception e) {
            logger.error("Erro ao tentar listar a montadora de id: " + id, e);
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Montadora> update(int id, @Valid MontadoraDTO montadoraDTO) {
        try {
            Montadora montadora = montadoraRepository.findById(id).orElse(null);
            if (montadora != null) {
                Montadora updateMontadora = montadoraDTO.toEntityUpdate(montadora);
                return CompletableFuture.completedFuture(this.montadoraRepository.save(updateMontadora));
            }
        } catch (Exception e) {
            logger.error("Erro ao tentar atualizar a montadora", e);
            return CompletableFuture.failedFuture(e);
        }
        return null;
    }

    @Async
    public CompletableFuture<Boolean> delete(int id) {
        try {
            Montadora montadora = this.montadoraRepository.findById(id).orElse(null);
            if (montadora != null) {
                this.montadoraRepository.delete(montadora);
                return CompletableFuture.completedFuture(true);
            }
            return CompletableFuture.completedFuture(false);
        } catch (Exception e) {
            logger.error("Erro ao deletar a montadora", e);
            return CompletableFuture.failedFuture(e);
        }
    }
}