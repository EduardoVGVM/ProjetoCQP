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

import com.projetopratico.cqp.dto.CarroDetalhesDTO;
import com.projetopratico.cqp.models.CarroDetalhes;
import com.projetopratico.cqp.services.CarroDetalhesService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/carroDetalhes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarroDetalhesController {
    private final CarroDetalhesService carroDetalhesService;

    @GetMapping
    public ResponseEntity<List<CarroDetalhes>> listAll() {
        List<CarroDetalhes> listCarroDetalhes = this.carroDetalhesService.listAll();
        return new ResponseEntity<>(listCarroDetalhes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarroDetalhes> create(@RequestBody @Valid CarroDetalhesDTO carroDetalhesDTO) {
        CarroDetalhes carroDetalhes = carroDetalhesService.create(carroDetalhesDTO);
        return new ResponseEntity<>(carroDetalhes, HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<CarroDetalhes> getById(@PathVariable int Id) {
        CarroDetalhes carroDetalhes = this.carroDetalhesService.getById(Id);
        if (carroDetalhes != null) {
            return new ResponseEntity<>(carroDetalhes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<CarroDetalhes> update(@PathVariable int Id,
            @RequestBody @Valid CarroDetalhesDTO carroDetalhesDTO) {
        CarroDetalhes carroDetalhes = carroDetalhesService.update(Id, carroDetalhesDTO);
        if (carroDetalhes != null) {
            return new ResponseEntity<>(carroDetalhes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> delete(@PathVariable int Id) {
        if (carroDetalhesService.delete(Id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
