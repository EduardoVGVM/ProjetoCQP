package com.projetopratico.cqp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetopratico.cqp.dto.CarroDTO;
import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.services.CarroService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/carro")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarroController {
    private final CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> listAll() {
        List<Carro> listCarros = this.carroService.listAll();
        return new ResponseEntity<>(listCarros, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Carro> create(@RequestBody @Valid CarroDTO carroDTO) {
        Carro Carro = carroService.create(carroDTO);
        return new ResponseEntity<>(Carro, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getById(@PathVariable int id) {
        Carro carro = this.carroService.getById(id);
        if (carro != null) {
            return new ResponseEntity<>(carro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> update(@PathVariable int id, @RequestBody @Valid CarroDTO carroDTO) {
        Carro carro = carroService.update(id, carroDTO);
        if (carro != null) {
            return new ResponseEntity<>(carro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        if (carroService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
