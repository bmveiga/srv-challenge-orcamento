package com.bmveiga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmveiga.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	List<Despesa> findAllByDescricao(String descricao);
}
