package com.bmveiga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmveiga.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	List<Receita> findAllByDescricao(String descricao);
}
