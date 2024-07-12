package com.fiap.entrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.entrega.entity.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    
}
