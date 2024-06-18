package com.projetopratico.cqp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetopratico.cqp.dto.CarroDetalhesDTO;
import com.projetopratico.cqp.models.CarroDetalhes;
import com.projetopratico.cqp.repositories.CarroDetalhesRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarroDetalhesService {
    private final CarroDetalhesRepository carroDetalhesRepository;

    public CarroDetalhes create(CarroDetalhesDTO carroDetalhesDTO) {
        CarroDetalhes carroDetalhes = carroDetalhesDTO.toEntity();
        return this.carroDetalhesRepository.save(carroDetalhes);
    }

    public List<CarroDetalhes> listAll() {
        List<CarroDetalhes> carroDetalhesList = carroDetalhesRepository.findAll();
        return carroDetalhesList.stream().collect(Collectors.toList());
    }

    public CarroDetalhes getById(int id) {
        return this.carroDetalhesRepository.findById(id).orElse(null);
    }

    public CarroDetalhes update(int id, @Valid CarroDetalhesDTO carroDetalhesDTO) {
        CarroDetalhes carroDetalhes = carroDetalhesRepository.findById(id).orElse(null);
        if (carroDetalhes != null) {
            CarroDetalhes updateCarroDetalhes = carroDetalhesDTO.toEntityUpdate(carroDetalhes);
            return this.carroDetalhesRepository.save(updateCarroDetalhes);
        }
        return null;
    }

    public boolean delete(int id) {
        CarroDetalhes carroDetalhes = this.carroDetalhesRepository.findById(id).orElse(null);
        if (carroDetalhes != null) {
            this.carroDetalhesRepository.delete(carroDetalhes);
            return true;
        }
        return false;
    }
}
