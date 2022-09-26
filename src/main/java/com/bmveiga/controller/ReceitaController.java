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

import com.bmveiga.model.Receita;
import com.bmveiga.service.ReceitaService;

@RestController
@RequestMapping("receitas")
public class ReceitaController {
	
	@Autowired
	private ReceitaService service;
	
	@PostMapping
	public ResponseEntity<Receita> novaReceita (@RequestBody Receita entrada){
		try {
			service.cadastrarReceita(entrada);		
			return new ResponseEntity<Receita>(entrada, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public List<Receita> buscarTodasReceitas(@RequestParam(required = false) String descricao){
		if(descricao != null) {
			return service.buscarPorDescricao(descricao);
		}
		return service.buscarTodasReceitas();
	}
	
	@GetMapping("/{id}")
	public Optional<Receita> buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Receita> alterarPorId(@PathVariable Long id, @RequestBody Receita entrada) {
		try {
			Receita receita = service.alterarReceita(id, entrada);
			return new ResponseEntity<Receita>(receita, HttpStatus.ACCEPTED);
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
	public List<Receita> buscarPorMes(@PathVariable int ano, @PathVariable int mes){
		return service.buscarPorMes(mes, ano);
	}
}
