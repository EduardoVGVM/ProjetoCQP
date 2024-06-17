package com.projetopratico.cqp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetopratico.cqp.models.CarroDetalhes;

@Repository
public interface CarroDetalhesRepository extends JpaRepository<CarroDetalhes, Integer>{ 
    @Query("SELECT FROM carro_detalhes, JOIN carro on carro_detalhes.carro_id = carro.id, JOIN montadora ON montadora.id = carro.montadora_id")
    List<CarroDetalhes> listAll();
}