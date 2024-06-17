package com.projetopratico.cqp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetopratico.cqp.dto.CarroDetalhesDTO;
import com.projetopratico.cqp.models.CarroDetalhes;
import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.repositories.CarroDetalhesRepository;
import com.projetopratico.cqp.repositories.CarroRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarroDetalhesService {
    private final CarroDetalhesRepository carroDetalhesRepository;
    private final CarroRepository carroRepository;

    public CarroDetalhes create(CarroDetalhesDTO carroDetalhesDTO) {
        Carro carro = this.carroRepository.findById(carroDetalhesDTO.getCarro_id()).orElse(null);

        if (carro != null) {
            CarroDetalhes carroDetalhes = carroDetalhesDTO.toEntity(carro);
            return this.carroDetalhesRepository.save(carroDetalhes);
        }
        return null;
    }

    public List<CarroDetalhes> listAll() {
        List<CarroDetalhes> carroDetalhesList = carroDetalhesRepository.listAll();
        return carroDetalhesList.stream().collect(Collectors.toList());
    }

    public CarroDetalhes getById(int id) {
        return this.carroDetalhesRepository.findById(id).orElse(null);
    }

    public CarroDetalhes update(int id, @Valid CarroDetalhesDTO carroDetalhesDTO) {
        CarroDetalhes carroDetalhes = this.carroDetalhesRepository.findById(id).orElse(null);
        Carro carro = this.carroRepository.findById(carroDetalhesDTO.getCarro_id()).orElse(null);

        if (carroDetalhes != null && carro != null) {
            carroDetalhes.setCarro(carro);
            carroDetalhes = carroDetalhesDTO.toEntityUpdate(carroDetalhes, carro);
            return this.carroDetalhesRepository.save(carroDetalhes);
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
