package com.projetopratico.cqp.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    @GetMapping
    public CompletableFuture<ResponseEntity<List<Montadora>>> listAll() {
        return montadoraService.listAll()
                .thenApply(montadoras -> ResponseEntity.ok().body(montadoras))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Operation(summary = "Request POST", description = "Insere uma montadora no DB")
    @PostMapping
    public CompletableFuture<ResponseEntity<Montadora>> create(@RequestBody @Valid MontadoraDTO montadoraDTO) {
        return montadoraService.create(montadoraDTO)
                .thenApply(carroCreate -> new ResponseEntity<>(carroCreate, HttpStatus.CREATED))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Operation(summary = "Request GET BY ID", description = "Busca uma montadora por id")
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Montadora>> getById(@PathVariable int id) {
        return montadoraService.getById(id)
                .thenApply(montadoraById -> {
                    if (montadoraById != null) {
                        return new ResponseEntity<>(montadoraById, HttpStatus.OK);
                    }
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    @Operation(summary = "Request PUT", description = "Atualiza uma montadora")
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Montadora>> update(@PathVariable int id,
            @RequestBody @Valid MontadoraDTO montadoraDTO) {
        return montadoraService.update(id, montadoraDTO)
                .thenApply(montadoraUpdate -> ResponseEntity.ok().body(montadoraUpdate))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Operation(summary = "Request DELETE", description = "Deleta uma montadora")
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<?>> delete(@PathVariable int id) {
        return montadoraService.delete(id)
                .thenApply(carroDelete -> {
                    if (carroDelete) {
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                });
    }
}
