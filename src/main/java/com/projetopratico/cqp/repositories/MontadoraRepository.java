package com.projetopratico.cqp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetopratico.cqp.models.Montadora;

@Repository
public interface MontadoraRepository extends JpaRepository<Montadora, Integer>{   
}
