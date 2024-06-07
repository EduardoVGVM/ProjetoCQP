package com.projetopratico.cqp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetopratico.cqp.models.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer>{
}