package com.projetopratico.cqp.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.projetopratico.cqp.dto.CarroDetalhesDTO;
import com.projetopratico.cqp.models.CarroDetalhes;
import com.projetopratico.cqp.repositories.CarroDetalhesRepository;

@ExtendWith(MockitoExtension.class)
class CarroDetalhesServiceTest {

    @Mock
    private CarroDetalhesRepository carroDetalhesRepository;

    @InjectMocks
    private CarroDetalhesService carroDetalhesService;

    private CarroDetalhesDTO carroDetalhesDTO;
    private CarroDetalhes carroDetalhes;

    @BeforeEach
    public void setUp() {
        carroDetalhes = CarroDetalhes.builder()
                .id(1)
                .urlDetalhes("url")
                .xpathCor("cor")
                .xpathModelo("modelo")
                .xpathNome("nome")
                .xpathPreco("preco")
                .xpathUrlImagem("imagem")
                .build();

        carroDetalhesDTO = CarroDetalhesDTO.builder()
                .urlDetalhes("url")
                .xpathCor("cor")
                .xpathModelo("modelo")
                .xpathNome("nome")
                .xpathPreco("preco")
                .xpathUrlImagem("imagem")
                .build();
    }

    @AfterEach
    void cleanUp() {
        Mockito.reset(carroDetalhesRepository);
    }

    @Test
    void CreateSuccess() throws Exception {
        when(carroDetalhesRepository.save(any(CarroDetalhes.class))).thenReturn(carroDetalhes);

        CompletableFuture<CarroDetalhes> result = carroDetalhesService.create(carroDetalhesDTO);

        assertNotNull(result);
        assertEquals(carroDetalhes, result.get());
    }

    @Test
    void CreateException() throws Exception {
        when(carroDetalhesRepository.save(any(CarroDetalhes.class)))
                .thenThrow(new RuntimeException("Erro ao tentar cadastrar Detalhes"));

        CompletableFuture<CarroDetalhes> result = carroDetalhesService.create(carroDetalhesDTO);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao tentar cadastrar Detalhes"));
        }
    }

    @Test
    void ListAllSuccess() throws Exception {
        when(carroDetalhesRepository.findAll()).thenReturn(Arrays.asList(carroDetalhes));

        CompletableFuture<List<CarroDetalhes>> result = carroDetalhesService.listAll();

        assertNotNull(result);
        assertEquals(1, result.get().size());
        assertEquals(carroDetalhes, result.get().get(0));
    }

    @Test
    void ListAllException() throws Exception {
        when(carroDetalhesRepository.findAll())
                .thenThrow(new RuntimeException("Erro ao tentar listar todos os Detalhes"));

        CompletableFuture<List<CarroDetalhes>> result = carroDetalhesService.listAll();

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao tentar listar todos os Detalhes"));
        }
    }

    @Test
    void GetByIdSuccess() throws Exception {
        when(carroDetalhesRepository.findById(1)).thenReturn(Optional.of(carroDetalhes));

        CompletableFuture<CarroDetalhes> result = carroDetalhesService.getById(1);

        assertNotNull(result);
        assertEquals(carroDetalhes, result.get());
    }

    @Test
    void GetByIdException() throws Exception {
        when(carroDetalhesRepository.findById(1))
                .thenThrow(new RuntimeException("Erro ao tentar listar os Detalhes por Id"));

        CompletableFuture<CarroDetalhes> result = carroDetalhesService.getById(1);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao tentar listar os Detalhes por Id"));
        }
    }

    @Test
    void UpdateSuccess() throws Exception {
        when(carroDetalhesRepository.findById(1)).thenReturn(Optional.of(carroDetalhes));
        when(carroDetalhesRepository.save(any(CarroDetalhes.class))).thenReturn(carroDetalhes);

        CompletableFuture<CarroDetalhes> result = carroDetalhesService.update(1,
                carroDetalhesDTO);

        assertNotNull(result);
        assertEquals(carroDetalhes, result.get());
    }

    @Test
    void UpdateException() throws Exception {
        when(carroDetalhesRepository.findById(1)).thenThrow(new RuntimeException("Erro ao tentar atualizar Detalhes"));

        CompletableFuture<CarroDetalhes> result = carroDetalhesService.update(1,
                carroDetalhesDTO);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao tentar atualizar Detalhes"));
        }
    }

    @Test
    void DeleteSuccess() throws Exception {
        when(carroDetalhesRepository.findById(1)).thenReturn(Optional.of(carroDetalhes));

        CompletableFuture<Boolean> result = carroDetalhesService.delete(1);

        assertNotNull(result);
        assertEquals(true, result.get());
    }

    @Test
    void DeleteException() throws Exception {
        when(carroDetalhesRepository.findById(1)).thenThrow(new RuntimeException("Database error"));

        CompletableFuture<Boolean> result = carroDetalhesService.delete(1);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Database error"));
        }
    }

}