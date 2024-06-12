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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "GET com sucesso") })
    @GetMapping
    public ResponseEntity<List<CarroDetalhes>> listAll() {
        List<CarroDetalhes> listCarroDetalhes = this.carroDetalhesService.listAll();
        return new ResponseEntity<>(listCarroDetalhes, HttpStatus.OK);
    }

    @Operation(summary = "Request POST", description = "Insere os detalhes de um carro no DB")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "POST com sucesso") })
    @PostMapping
    public ResponseEntity<CarroDetalhes> create(@RequestBody @Valid CarroDetalhesDTO carroDetalhesDTO) {
        CarroDetalhes carroDetalhes = carroDetalhesService.create(carroDetalhesDTO);
        return new ResponseEntity<>(carroDetalhes, HttpStatus.CREATED);
    }

    @Operation(summary = "Request GET BY ID", description = "Busca pelos detalhes de um carro por id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "GET BY ID com sucesso") })
    @GetMapping("/{id}")
    public ResponseEntity<CarroDetalhes> getById(@PathVariable int id) {
        CarroDetalhes carroDetalhes = this.carroDetalhesService.getById(id);
        if (carroDetalhes != null) {
            return new ResponseEntity<>(carroDetalhes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Request PUT", description = "Atualiza os detalhes de um carro")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "PUT com sucesso") })
    @PutMapping("/{id}")
    public ResponseEntity<CarroDetalhes> update(@PathVariable int id,
            @RequestBody @Valid CarroDetalhesDTO carroDetalhesDTO) {
        CarroDetalhes carroDetalhes = carroDetalhesService.update(id, carroDetalhesDTO);
        if (carroDetalhes != null) {
            return new ResponseEntity<>(carroDetalhes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Request DELETE", description = "Deleta os detalhes de um carro")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "DELETE com sucesso") })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        if (carroDetalhesService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
