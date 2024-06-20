package com.projetopratico.cqp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.services.CrawlerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Crawler", description = "Controlador do Crawler")
@RestController
@RequestMapping("/crawler")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CrawlerController {
    private final CrawlerService crawlerService;

    @Operation(summary = "Request PUT", description = "Atualiza os detalhes de um carro")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "PUT com sucesso") })
    @PutMapping("/{id}")
    public ResponseEntity<Carro> update(@PathVariable int id) {
        Carro carro = crawlerService.update(id);
        if (carro != null) {
            return new ResponseEntity<>(carro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
