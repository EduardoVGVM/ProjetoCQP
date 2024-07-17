package com.projetopratico.cqp.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
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

import com.projetopratico.cqp.dto.CarroDTO;
import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.models.CarroDetalhes;
import com.projetopratico.cqp.models.Montadora;
import com.projetopratico.cqp.repositories.CarroDetalhesRepository;
import com.projetopratico.cqp.repositories.CarroRepository;
import com.projetopratico.cqp.repositories.MontadoraRepository;

@ExtendWith(MockitoExtension.class)
public class CarroServiceTest {

    @Mock
    private CarroDetalhesRepository carroDetalhesRepository;
    @Mock
    private MontadoraRepository montadoraRepository;
    @Mock
    private CarroRepository carroRepository;

    @InjectMocks
    private CarroService carroService;

    private CarroDTO carroDTO;

    @BeforeEach
    public void setUp() {

        carroDTO = CarroDTO.builder()
                .nome("nome")
                .modelo("modelo")
                .cor("cor")
                .preco(3000)
                .urlImagem("url")
                .montadora_id(1)
                .carroDetalhes_id(1)
                .build();
    }

    @AfterEach
    void cleanUp() {
        Mockito.reset(carroDetalhesRepository);
    }

    @Test
    public void CreateSuccess() throws Exception {
        Montadora montadora = new Montadora();
        CarroDetalhes carroDetalhes = new CarroDetalhes();
        Carro carro = carroDTO.toEntity(montadora, carroDetalhes);

        when(montadoraRepository.findById(carroDTO.getMontadora_id())).thenReturn(Optional.of(montadora));
        when(carroDetalhesRepository.findById(carroDTO.getCarroDetalhes_id())).thenReturn(Optional.of(carroDetalhes));
        when(carroRepository.save(any(Carro.class))).thenReturn(carro);

        CompletableFuture<Carro> result = carroService.create(carroDTO);

        assertNotNull(result);
        assertEquals(carro, result.get());
    }

    @Test
    public void CreateException() throws Exception {
        when(montadoraRepository.findById(anyInt())).thenThrow(new RuntimeException("Erro ao criar Carro"));

        CompletableFuture<Carro> result = carroService.create(carroDTO);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao criar Carro"));
        }
    }

    @Test
    public void CreateMontadoraDetalhesException() throws Exception {
        when(montadoraRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(carroDetalhesRepository.findById(anyInt())).thenReturn(Optional.empty());

        CompletableFuture<Carro> result = carroService.create(carroDTO);

        assertNull(result);
    }

    @Test
    public void ListAllSuccess() throws Exception {
        List<Carro> carros = Arrays.asList(new Carro(), new Carro());

        when(carroRepository.findAll()).thenReturn(carros);

        CompletableFuture<List<Carro>> result = carroService.listAll();

        assertNotNull(result);
        assertEquals(carros, result.get());
    }

    @Test
    public void ListAllException() throws Exception {
        when(carroRepository.findAll()).thenThrow(new RuntimeException("Erro ao listar todos os Carros"));

        CompletableFuture<List<Carro>> result = carroService.listAll();

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao listar todos os Carros"));
        }
    }

    @Test
    public void GetByIdSuccess() throws Exception {
        Carro carro = new Carro();
        when(carroRepository.findById(1)).thenReturn(Optional.of(carro));

        CompletableFuture<Carro> result = carroService.getById(1);

        assertNotNull(result);
        assertEquals(carro, result.get());
    }

    @Test
    public void GetByIdException() throws Exception {
        when(carroRepository.findById(1)).thenThrow(new RuntimeException("Erro ao listar carro por Id"));

        CompletableFuture<Carro> result = carroService.getById(1);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao listar carro por Id"));
        }
    }

    @Test
    public void UpdateSuccess() throws Exception {
        Carro existingCarro = new Carro();
        Montadora montadora = new Montadora();
        CarroDetalhes carroDetalhes = new CarroDetalhes();
        Carro updatedCarro = carroDTO.toEntityUpdate(existingCarro, montadora, carroDetalhes);

        when(carroRepository.findById(1)).thenReturn(Optional.of(existingCarro));
        when(montadoraRepository.findById(carroDTO.getMontadora_id())).thenReturn(Optional.of(montadora));
        when(carroDetalhesRepository.findById(carroDTO.getCarroDetalhes_id())).thenReturn(Optional.of(carroDetalhes));
        when(carroRepository.save(any(Carro.class))).thenReturn(updatedCarro);

        CompletableFuture<Carro> result = carroService.update(1, carroDTO);

        assertNotNull(result);
        assertEquals(updatedCarro, result.get());
    }

    @Test
    public void UpdateException() throws Exception {
        when(carroRepository.findById(anyInt())).thenThrow(new RuntimeException("Erro ao atualizar carro"));

        CompletableFuture<Carro> result = carroService.update(1, carroDTO);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao atualizar carro"));
        }
    }

    @Test
    public void DeleteSuccess() throws Exception {
        Carro carro = new Carro();
        when(carroRepository.findById(1)).thenReturn(Optional.of(carro));
        doNothing().when(carroRepository).delete(carro);

        CompletableFuture<Boolean> result = carroService.delete(1);

        assertNotNull(result);
        assertTrue(result.get());
    }

    @Test
    public void DeleteException() throws Exception {
        when(carroRepository.findById(1)).thenThrow(new RuntimeException("Erro ao deletar carro"));

        CompletableFuture<Boolean> result = carroService.delete(1);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao deletar carro"));
        }
    }

}