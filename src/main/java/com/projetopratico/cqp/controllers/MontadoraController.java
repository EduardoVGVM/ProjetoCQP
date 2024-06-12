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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Montadora", description = "Controlador da Montadora")
@RestController
@RequestMapping("/montadora")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MontadoraController {
    private final MontadoraService montadoraService;

    @Operation(summary = "Request GET", description = "Traz todos as montadoras do DB")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "GET com sucesso") })
    @GetMapping
    public ResponseEntity<List<Montadora>> listAll() {
        List<Montadora> listMontadoras = this.montadoraService.listAll();
        return new ResponseEntity<>(listMontadoras, HttpStatus.OK);
    }

    @Operation(summary = "Request POST", description = "Insere uma montadora no DB")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "POST com sucesso") })
    @PostMapping
    public ResponseEntity<Montadora> create(@RequestBody @Valid MontadoraDTO montadoraDTO) {
        Montadora montadora = montadoraService.create(montadoraDTO);
        return new ResponseEntity<>(montadora, HttpStatus.CREATED);
    }

    @Operation(summary = "Request GET BY ID", description = "Busca uma montadora por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "GET BY ID com sucesso") })
    @GetMapping("/{id}")
    public ResponseEntity<Montadora> getById(@PathVariable int id) {
        Montadora montadora = this.montadoraService.getById(id);
        if (montadora != null) {
            return new ResponseEntity<>(montadora, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Request PUT", description = "Atualiza uma montadora")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "PUT com sucesso") })
    @PutMapping("/{id}")
    public ResponseEntity<Montadora> update(@PathVariable int id, @RequestBody @Valid MontadoraDTO montadoraDTO) {
        Montadora montadora = montadoraService.update(id, montadoraDTO);
        if (montadora != null) {
            return new ResponseEntity<>(montadora, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Request DELETE", description = "Deleta umaa montadora")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "DELETE com sucesso") })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        if (montadoraService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
