package com.bmveiga.service;

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
	
	public void isDuplicado(Receita receita) throws Exception {
		List<Receita> duplicatas = repository.findAllByDescricao(receita.getDescricao());
		
		if(!duplicatas.isEmpty()) {
			int mesEntrada = capturarMes(receita.getData());
			
			for (Receita duplicada : duplicatas) {
				int mesDuplicata = capturarMes(duplicada.getData());
				if(mesDuplicata == mesEntrada) {
					throw new Exception("Entrada duplicada!");
				}
			}
		}
	}
	
	public Receita alterarReceita(Long id, Receita receita) throws Exception {
		isDuplicado(receita);
		Receita original = validarReceitaExiste(id);
		BeanUtils.copyProperties(receita, original, "id");
		return repository.save(original);
		
	}
	
	public int capturarMes(Date data) {
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
