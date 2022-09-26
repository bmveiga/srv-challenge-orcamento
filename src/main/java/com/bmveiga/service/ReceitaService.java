package com.bmveiga.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.bmveiga.model.Receita;
import com.bmveiga.repository.ReceitaRepository;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository repository;
	
	public Receita cadastrarReceita(Receita receita) throws Exception {
		isDuplicado(receita, receita.getId());
		repository.save(receita);
		return receita;
	}
	
	public List<Receita> buscarTodasReceitas(){
		return repository.findAll();
	}
	
	public Optional<Receita> buscarPorId(Long id) {
		return repository.findById(id);
	}
	
	public void deletarPorId(Long id) {
		repository.deleteById(id);
	}
	
	public Receita alterarReceita(Long id, Receita receita) throws Exception {
		isDuplicado(receita, id);
		Receita original = validarReceitaExiste(id);
		BeanUtils.copyProperties(receita, original, "id");
		return repository.save(original);
	}
	
	public void isDuplicado(Receita receita, Long id) throws Exception {
		List<Receita> duplicatas = repository.findAllByDescricao(receita.getDescricao());
		
		if(!duplicatas.isEmpty()) {
			int mesEntrada = capturarMes(receita.getData());
			int anoEntrada = capturarAno(receita.getData());
			
			for (Receita duplicada : duplicatas) {
				Long idAtual = duplicada.getId();
				int mesDuplicata = capturarMes(duplicada.getData());
				int anoReceita = capturarAno(duplicada.getData());
				if(id != idAtual && anoReceita == anoEntrada && mesDuplicata == mesEntrada) {
					throw new Exception("Entrada duplicada!");
				}
			}
		}
	}
	
	public List<Receita> buscarPorDescricao(String descricao){
		return repository.findAllByDescricao(descricao);
	}
	
	public List<Receita> buscarPorMes(int mes, int ano){
		List<Receita> receitas = repository.findAll();
		List<Receita> receitasFiltradas = new ArrayList<>();
		
		for (Receita receita : receitas) {
			int anoReceita = capturarAno(receita.getData());
			if(anoReceita == ano) {
				int mesReceita = capturarMes(receita.getData());
				if(mesReceita == mes) {
					receitasFiltradas.add(receita);
				}
			}
		}
		return receitasFiltradas;
	}
	
	private int capturarAno(Date data) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		return gc.get(gc.YEAR);
	}

	private int capturarMes(Date data) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		return gc.get(gc.MONTH);
	}
	
	private Receita validarReceitaExiste(Long id) {
		Optional<Receita> receita = repository.findById(id);
		if (receita.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return receita.get();
	}

}
