package com.projeto.sistema.repository;


import com.projeto.sistema.model.Cliente;
import com.projeto.sistema.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
