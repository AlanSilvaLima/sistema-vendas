package com.projeto.sistema.repository;

import com.projeto.sistema.model.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
