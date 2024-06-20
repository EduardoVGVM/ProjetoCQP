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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "GET com sucesso") })
    @GetMapping
    public ResponseEntity<List<Carro>> listAll() {
        List<Carro> listCarros = this.carroService.listAll();
        return new ResponseEntity<>(listCarros, HttpStatus.OK);
    }

    @Operation(summary = "Request POST", description = "Insere um carro no DB")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "POST com sucesso") })
    @PostMapping
    public ResponseEntity<Carro> create(@RequestBody @Valid CarroDTO carroDTO) {
        Carro carro = carroService.create(carroDTO);
        return new ResponseEntity<>(carro, HttpStatus.CREATED);
    }

    @Operation(summary = "Request GET BY ID", description = "Busca um carro por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "GET BY ID com sucesso") })
    @GetMapping("/{id}")
    public ResponseEntity<Carro> getById(@PathVariable int id) {
        Carro carro = this.carroService.getById(id);
        if (carro != null) {
            return new ResponseEntity<>(carro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Request PUT", description = "Atualiza um carro")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "PUT com sucesso") })
    @PutMapping("/{id}")
    public ResponseEntity<Carro> update(@PathVariable int id, @RequestBody @Valid CarroDTO carroDTO) {
        Carro carro = carroService.update(id, carroDTO);
        if (carro != null) {
            return new ResponseEntity<>(carro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Request DELETE", description = "Deleta um carro")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "DELETE com sucesso") })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        if (carroService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
