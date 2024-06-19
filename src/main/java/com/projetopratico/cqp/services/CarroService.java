package com.projetopratico.cqp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Carro create(CarroDTO carroDTO) {
        Montadora montadora = this.montadoraRepository.findById(carroDTO.getMontadora_id()).orElse(null);
        CarroDetalhes carroDetalhes = this.carroDetalhesRepository.findById(carroDTO.getCarroDetalhes_id())
                .orElse(null);

        if (montadora != null && carroDetalhes != null) {
            Carro carro = carroDTO.toEntity(montadora, carroDetalhes);
            return this.carroRepository.save(carro);
        }
        return null;
    }

    public List<Carro> listAll() {
        List<Carro> carros = carroRepository.findAll();
        return carros.stream().collect(Collectors.toList());
    }

    public Carro getById(int id) {
        return this.carroRepository.findById(id).orElse(null);
    }

    public Carro update(int id, @Valid CarroDTO carroDTO) {
        Carro carro = this.carroRepository.findById(id).orElse(null);
        Montadora montadora = this.montadoraRepository.findById(carroDTO.getMontadora_id()).orElse(null);
        CarroDetalhes carroDetalhes = this.carroDetalhesRepository.findById(carroDTO.getCarroDetalhes_id())
                .orElse(null);

        if (carro != null && montadora != null && carroDetalhes != null) {
            carro.setMontadora(montadora);
            carro = carroDTO.toEntityUpdate(carro, montadora, carroDetalhes);
            return this.carroRepository.save(carro);
        }
        return null;
    }

    public boolean delete(int id) {
        Carro carro = this.carroRepository.findById(id).orElse(null);
        if (carro != null) {
            this.carroRepository.delete(carro);
            return true;
        }
        return false;
    }
}
