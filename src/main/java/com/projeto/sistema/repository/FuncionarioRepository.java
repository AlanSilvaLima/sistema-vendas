package com.projeto.sistema.repository;


import com.projeto.sistema.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
