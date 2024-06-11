package com.projetopratico.cqp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetopratico.cqp.dto.MontadoraDTO;
import com.projetopratico.cqp.models.Montadora;
import com.projetopratico.cqp.repositories.MontadoraRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MontadoraService {
    private final MontadoraRepository montadoraRepository;

    public Montadora create(MontadoraDTO montadoraDTO) {
        Montadora montadora = montadoraDTO.toEntity();
        return this.montadoraRepository.save(montadora);
    }

    public List<Montadora> listAll() {
        List<Montadora> montadoras = montadoraRepository.findAll();
        return montadoras.stream().collect(Collectors.toList());
    }

    public Montadora getById(int id) {
        return this.montadoraRepository.findById(id).orElse(null);
    }

    public Montadora update(int id, @Valid MontadoraDTO montadoraDTO) {
        Montadora montadora = montadoraRepository.findById(id).orElse(null);
        if (montadora != null) {
            Montadora updateMontadora = montadoraDTO.toEntityUpdate(montadora);
            return this.montadoraRepository.save(updateMontadora);
        }
        return null;
    }

    public boolean delete(int id) {
        Montadora montadora = this.montadoraRepository.findById(id).orElse(null);
        if (montadora != null) {
            this.montadoraRepository.delete(montadora);
            return true;
        }
        return false;
    }
}