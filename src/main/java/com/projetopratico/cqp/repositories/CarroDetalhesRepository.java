package com.projetopratico.cqp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetopratico.cqp.models.CarroDetalhes;

@Repository
public interface CarroDetalhesRepository extends JpaRepository<CarroDetalhes, Long>{ 
}