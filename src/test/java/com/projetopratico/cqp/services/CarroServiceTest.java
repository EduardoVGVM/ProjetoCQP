package com.projetopratico.cqp.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.projetopratico.cqp.dto.CarroDetalhesDTO;
import com.projetopratico.cqp.dto.MontadoraDTO;
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
    private MontadoraService montadoraService;
    @InjectMocks
    private CarroDetalhesService carroDetalhesService;
    @InjectMocks
    private CarroService carroService;

    private Carro carro;
    private CarroDTO carroDTO;
    private CarroDetalhes carroDetalhes;
    private CarroDetalhesDTO carroDetalhesDTO;
    private Montadora montadora;
    private MontadoraDTO montadoraDTO;

    @BeforeEach
    public void setUp() {
        montadora = Montadora.builder()
                .id(1)
                .nome("Toyota")
                .build();

        montadoraDTO = MontadoraDTO.builder()
                .nome("Toyota")
                .build();

        carroDetalhes = CarroDetalhes.builder()
                .id(1)
                .urlDetalhes("urlDetalhes")
                .xpathCor("xpathCor")
                .xpathModelo("xpathModelo")
                .xpathNome("xpathNome")
                .xpathPreco("xpathPreco")
                .xpathUrlImagem("xpathUrlImagem")
                .build();

        carroDetalhesDTO = CarroDetalhesDTO.builder()
                .urlDetalhes("urlDetalhes")
                .xpathCor("xpathCor")
                .xpathModelo("xpathModelo")
                .xpathNome("xpathNome")
                .xpathPreco("xpathPreco")
                .xpathUrlImagem("xpathUrlImagem")
                .build();

        carro = Carro.builder()
                .id(1)
                .nome("nome")
                .modelo("modelo")
                .cor("cor")
                .preco(3000)
                .urlImagem("url")
                .montadora(montadora)
                .carroDetalhes(carroDetalhes)
                .build();

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
    void CreateSuccess() throws Exception {
        when(carroRepository.save(any(Carro.class))).thenReturn(carro);

        CompletableFuture<Carro> result = carroService.create(carroDTO);

        assertNotNull(result);

        assertEquals(carro.getNome(), result.get().getNome());
        assertEquals(carro.getModelo(), result.get().getModelo());
        assertEquals(carro.getCor(), result.get().getCor());
        assertEquals(carro.getPreco(), result.get().getPreco());
        assertEquals(carro.getUrlImagem(), result.get().getUrlImagem());
        assertEquals(carro.getMontadora(), result.get().getMontadora());
        assertEquals(carro.getCarroDetalhes(), result.get().getCarroDetalhes());
    }

    @Test
    void CreateException() throws Exception {
        when(carroDetalhesRepository.save(any(CarroDetalhes.class)))
                .thenThrow(new RuntimeException("Erro ao tentar cadastrar Carro"));

        CompletableFuture<CarroDetalhes> result = carroDetalhesService.create(carroDetalhesDTO);

        try {
            result.get();
        } catch (ExecutionException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertTrue(e.getCause().getMessage().contains("Erro ao tentar cadastrar Carro"));
        }
    }

    @Test
    void ListAllSuccess() throws Exception {
        when(carroDetalhesRepository.findAll()).thenReturn(Arrays.asList(carroDetalhes));

        CompletableFuture<List<CarroDetalhes>> result = carroDetalhesService.listAll();

        assertNotNull(result);
        assertEquals(1, result.get().size());
        assertEquals(carroDetalhes.getUrlDetalhes(), result.get().get(0).getUrlDetalhes());
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
        assertEquals(carroDetalhes.getUrlDetalhes(), result.get().getUrlDetalhes());
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
        assertEquals(carroDetalhes.getUrlDetalhes(), result.get().getUrlDetalhes());
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