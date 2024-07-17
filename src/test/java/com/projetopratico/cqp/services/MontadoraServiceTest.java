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

import com.projetopratico.cqp.dto.MontadoraDTO;
import com.projetopratico.cqp.models.Montadora;
import com.projetopratico.cqp.repositories.MontadoraRepository;

@ExtendWith(MockitoExtension.class)
class MontadoraServiceTest {

    @Mock
    private MontadoraRepository montadoraRepository;

    @InjectMocks
    private MontadoraService montadoraService;

    private MontadoraDTO montadoraDTO;
    private Montadora montadora;

    @BeforeEach
    public void setUp() {
        montadora = Montadora.builder()
                .id(1)
                .nome("Toyota")
                .build();

        montadoraDTO = MontadoraDTO.builder()
                .nome("Toyota")
                .build();
    }

    @AfterEach
    void cleanUp() {
        Mockito.reset(montadoraRepository);
    }

    @Test
    void CreateSuccess() throws Exception {
        when(montadoraRepository.save(any(Montadora.class))).thenReturn(montadora);

        CompletableFuture<Montadora> result = montadoraService.create(montadoraDTO);

        assertNotNull(result);
        assertEquals(montadora, result.get());
    }

    @Test
    void CreateException() throws Exception {
        when(montadoraRepository.save(any(Montadora.class)))
                .thenThrow(new RuntimeException("Erro ao cadastrar Montadora"));

        CompletableFuture<Montadora> result = montadoraService.create(montadoraDTO);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao cadastrar Montadora"));
        }
    }

    @Test
    void ListAllSuccess() throws Exception {
        when(montadoraRepository.findAll()).thenReturn(Arrays.asList(montadora));

        CompletableFuture<List<Montadora>> result = montadoraService.listAll();

        assertNotNull(result);
        assertEquals(1, result.get().size());
        assertEquals(montadora, result.get().get(0));
    }

    @Test
    void ListAllException() throws Exception {
        when(montadoraRepository.findAll()).thenThrow(new RuntimeException("Erro ao tentar listar montadoras"));

        CompletableFuture<List<Montadora>> result = montadoraService.listAll();

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao tentar listar montadoras"));
        }
    }

    @Test
    void GetByIdSuccess() throws Exception {
        when(montadoraRepository.findById(1)).thenReturn(Optional.of(montadora));

        CompletableFuture<Montadora> result = montadoraService.getById(1);

        assertNotNull(result);
        assertEquals(montadora, result.get());
    }

    @Test
    void GetByIdException() throws Exception {
        when(montadoraRepository.findById(1))
                .thenThrow(new RuntimeException("Erro ao tentar encontrar montadora por Id"));

        CompletableFuture<Montadora> result = montadoraService.getById(1);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao tentar encontrar montadora por Id"));
        }
    }

    @Test
    void UpdateSuccess() throws Exception {
        when(montadoraRepository.findById(1)).thenReturn(Optional.of(montadora));
        when(montadoraRepository.save(any(Montadora.class))).thenReturn(montadora);

        CompletableFuture<Montadora> result = montadoraService.update(1, montadoraDTO);

        assertNotNull(result);
        assertEquals(montadora, result.get());
    }

    @Test
    void UpdateException() throws Exception {
        when(montadoraRepository.findById(1)).thenThrow(new RuntimeException("Erro ao tentar atualizar montadora"));

        CompletableFuture<Montadora> result = montadoraService.update(1, montadoraDTO);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao tentar atualizar montadora"));
        }
    }

    @Test
    void DeleteSuccess() throws Exception {
        when(montadoraRepository.findById(1)).thenReturn(Optional.of(montadora));

        CompletableFuture<Boolean> result = montadoraService.delete(1);

        assertNotNull(result);
        assertEquals(true, result.get());
    }

    @Test
    void DeleteException() throws Exception {
        when(montadoraRepository.findById(1)).thenThrow(new RuntimeException("Erro ao tentar deletar montadora"));

        CompletableFuture<Boolean> result = montadoraService.delete(1);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao tentar deletar montadora"));
        }
    }

}