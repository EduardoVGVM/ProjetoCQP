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

import com.projetopratico.cqp.dto.CarroDTO;
import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.services.CarroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "Carro", description = "Controlador do Carro")
@RestController
@RequestMapping("/carro")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarroController {
    private final CarroService carroService;

    @Operation(summary = "Request GET", description = "Traz todos os carros do DB")
    @GetMapping
    public CompletableFuture<ResponseEntity<List<Carro>>> listAll() {
        return carroService.listAll()
                .thenApply(carros -> ResponseEntity.ok().body(carros))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Operation(summary = "Request POST", description = "Insere um carro no DB")
    @PostMapping
    public CompletableFuture<ResponseEntity<Carro>> create(@RequestBody @Valid CarroDTO carroDTO) {
        return carroService.create(carroDTO)
                .thenApply(carroCreate -> new ResponseEntity<>(carroCreate, HttpStatus.CREATED))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Operation(summary = "Request GET BY ID", description = "Busca um carro por id")
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Carro>> getById(@PathVariable int id) {
        return carroService.getById(id)
                .thenApply(carroById -> {
                    if (carroById != null) {
                        return new ResponseEntity<>(carroById, HttpStatus.OK);
                    }
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    @Operation(summary = "Request PUT", description = "Atualiza um carro")
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Carro>> update(@PathVariable int id,
            @RequestBody @Valid CarroDTO carroDTO) {
        return carroService.update(id, carroDTO)
                .thenApply(carroUpdate -> ResponseEntity.ok().body(carroUpdate))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Operation(summary = "Request DELETE", description = "Deleta um carro")
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<?>> delete(@PathVariable int id) {
        return carroService.delete(id)
                .thenApply(carroDelete -> {
                    if (carroDelete) {
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                });
    }
}
