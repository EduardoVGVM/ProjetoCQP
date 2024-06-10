package com.projetopratico.cqp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetopratico.cqp.dto.CarroDTO;
import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.repositories.CarroRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarroService {
    private final CarroRepository carroRepository;

    public Carro create(CarroDTO carroDTO) {
        Carro carro = carroDTO.toEntity();
        return this.carroRepository.save(carro);
    }

    public List<Carro> listAll() {
        List<Carro> carros = carroRepository.findAll();
        return carros.stream().collect(Collectors.toList());
    }

    public Carro getById(int Id) {
        return this.carroRepository.findById(Id).orElse(null);
    }

    public Carro update(int Id, @Valid CarroDTO carroDTO) {
        Carro carro = this.carroRepository.findById(Id).orElse(null);
        if (carro != null) {
            Carro updateCarro = carroDTO.toEntityUpdate(carro);
            return this.carroRepository.save(updateCarro);
        }
        return null;
    }

    public boolean delete(int Id) {
        Carro carro = this.carroRepository.findById(Id).orElse(null);
        if (carro != null) {
            this.carroRepository.delete(carro);
            return true;
        }
        return false;
    }
}
