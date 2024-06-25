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

import com.projetopratico.cqp.dto.CarroDetalhesDTO;
import com.projetopratico.cqp.models.CarroDetalhes;
import com.projetopratico.cqp.services.CarroDetalhesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "CarroDetalhes", description = "Controlador dos Detalhes")
@RestController
@RequestMapping("/carroDetalhes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarroDetalhesController {
    private final CarroDetalhesService carroDetalhesService;

    @Operation(summary = "Request GET", description = "Traz os detalhes de todos os carros no DB")
    @GetMapping
    public CompletableFuture<ResponseEntity<List<CarroDetalhes>>> listAll() {
        return carroDetalhesService.listAll()
                .thenApply(CarroDetalhesList -> ResponseEntity.ok().body(CarroDetalhesList))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Operation(summary = "Request POST", description = "Insere os detalhes de um carro no DB")
    @PostMapping
    public CompletableFuture<ResponseEntity<CarroDetalhes>> create(
            @RequestBody @Valid CarroDetalhesDTO carroDetalhesDTO) {
        return carroDetalhesService.create(carroDetalhesDTO)
                .thenApply(carroDetalhesCreate -> new ResponseEntity<>(carroDetalhesCreate, HttpStatus.CREATED))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Operation(summary = "Request GET BY ID", description = "Busca pelos detalhes de um carro por id")
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<CarroDetalhes>> getById(@PathVariable int id) {
        return carroDetalhesService.getById(id)
                .thenApply(carroDetalhesById -> {
                    if (carroDetalhesById != null) {
                        return new ResponseEntity<>(carroDetalhesById, HttpStatus.OK);
                    }
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    @Operation(summary = "Request PUT", description = "Atualiza os detalhes de um carro")
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<CarroDetalhes>> update(@PathVariable int id,
            @RequestBody @Valid CarroDetalhesDTO carroDetalhesDTO) {
        return carroDetalhesService.update(id, carroDetalhesDTO)
                .thenApply(carroDetalhesUpdate -> ResponseEntity.ok().body(carroDetalhesUpdate))
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Operation(summary = "Request DELETE", description = "Deleta os detalhes de um carro")
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<?>> delete(@PathVariable int id) {
        return carroDetalhesService.delete(id)
                .thenApply(carroDelete -> {
                    if (carroDelete) {
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                });
    }
}
