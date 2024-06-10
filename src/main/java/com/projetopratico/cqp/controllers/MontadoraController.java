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

import com.projetopratico.cqp.dto.MontadoraDTO;
import com.projetopratico.cqp.models.Montadora;
import com.projetopratico.cqp.services.MontadoraService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/montadora")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MontadoraController {
    private final MontadoraService montadoraService;

    @GetMapping
    public ResponseEntity<List<Montadora>> listAll() {
        List<Montadora> listMontadoras = this.montadoraService.listAll();
        return new ResponseEntity<>(listMontadoras, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Montadora> create(@RequestBody @Valid MontadoraDTO montadoraDTO) {
        Montadora montadora = montadoraService.create(montadoraDTO);
        return new ResponseEntity<>(montadora, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Montadora> getById(@PathVariable int Id) {
        Montadora montadora = this.montadoraService.getById(Id);
        if (montadora != null) {
            return new ResponseEntity<>(montadora, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Montadora> update(@PathVariable int Id, @RequestBody @Valid MontadoraDTO montadoraDTO) {
        Montadora montadora = montadoraService.update(Id, montadoraDTO);
        if(montadora != null) {
            return new ResponseEntity<>(montadora, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int Id) {
        if(montadoraService.delete(Id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
