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

import com.bmveiga.enums.Categoria;
import com.bmveiga.model.Despesa;
import com.bmveiga.model.Receita;
import com.bmveiga.repository.DespesaRepository;

@Service
public class DespesaService {
	
	@Autowired
	private DespesaRepository repository;
	
	public Despesa cadastrarDespesa(Despesa despesa) throws Exception {
		isDuplicado(despesa, despesa.getId());
		repository.save(despesa);
		return despesa;
	}
	
	public List<Despesa> buscarTodasDespesas(){
		return repository.findAll();
	}
	
	public Optional<Despesa> buscarPorId(Long id) {
		return repository.findById(id);
	}
	
	public Despesa alterarDespesa(Long id, Despesa despesa) throws Exception {
		isDuplicado(despesa, id);
		Despesa original = validarDespesaExiste(id);
		BeanUtils.copyProperties(despesa, original, "id");
		return repository.save(original);
	}
	
	public void deletarPorId(Long id) {
		repository.deleteById(id);
	}
	
	public void isDuplicado(Despesa despesa, Long id) throws Exception {
		List<Despesa> duplicatas = repository.findAllByDescricao(despesa.getDescricao());
		
		if(!duplicatas.isEmpty()) {
			int mesEntrada = capturarMes(despesa.getData());
			int anoEntrada = capturarAno(despesa.getData());
			
			for (Despesa duplicada : duplicatas) {
				Long idAtual = duplicada.getId();
				
				int mesDuplicata = capturarMes(duplicada.getData());
				int anoReceita = capturarAno(duplicada.getData());
				
				if(id != idAtual && anoReceita == anoEntrada && mesDuplicata == mesEntrada) {
					throw new Exception("Entrada duplicada!");
				}
			}
		}
	}
	
	public Despesa validarCategoria(Despesa despesa) {
		if (despesa.getCategoria() == null) {
			despesa.setCategoria(Categoria.OUTROS);
		}
		return despesa;
	}

	public List<Despesa> buscarPorDescricao(String descricao) {
		return repository.findAllByDescricao(descricao);
	}

	public List<Despesa> buscarPorMes(int mes, int ano){
		List<Despesa> despesas = buscarTodasDespesas();
		List<Despesa> despesasFiltradas = new ArrayList<>();
		
		for (Despesa despesa : despesas) {
			int anoDespesa = capturarAno(despesa.getData());
			if(anoDespesa == ano) {
				int mesDespesa = capturarMes(despesa.getData());
				if(mesDespesa == mes - 1) {
					despesasFiltradas.add(despesa);
				}
			}
		}
		return despesasFiltradas;
	}
	
	private int capturarMes(Date data) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		return gc.get(gc.MONTH);
	}
	
	private int capturarAno(Date data) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		return gc.get(gc.YEAR);
	}
	
	private Despesa validarDespesaExiste(Long id) {
		Optional<Despesa> despesa = repository.findById(id);
		if (despesa.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return despesa.get();
	}

}
