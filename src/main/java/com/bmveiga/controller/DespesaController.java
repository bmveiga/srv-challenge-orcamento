package com.bmveiga.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmveiga.model.Despesa;
import com.bmveiga.model.Receita;
import com.bmveiga.service.DespesaService;

@RestController
@RequestMapping("despesas")
public class DespesaController {
	
	@Autowired
	private DespesaService service;
	
	@PostMapping
	public ResponseEntity<Despesa> novaDespesa (@RequestBody Despesa entrada){
		try {
			Despesa despesa = service.validarCategoria(entrada);
			service.cadastrarDespesa(despesa);			
			return new ResponseEntity<Despesa>(despesa, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public List<Despesa> buscarTodasDespesas(@RequestParam(required = false) String descricao){
		if(descricao != null) {
			return service.buscarPorDescricao(descricao);
		}
		return service.buscarTodasDespesas();
	}
	
	@GetMapping("/{id}")
	public Optional<Despesa> buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Despesa> alterarPorId(@PathVariable Long id, @RequestBody Despesa entrada) {
		try {
			Despesa receita = service.alterarDespesa(id, entrada);
			return new ResponseEntity<Despesa>(receita, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
		service.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{ano}/{mes}")
	public List<Despesa> buscarPorMes(@PathVariable int ano, @PathVariable int mes){
		return service.buscarPorMes(mes, ano);
	}
}
