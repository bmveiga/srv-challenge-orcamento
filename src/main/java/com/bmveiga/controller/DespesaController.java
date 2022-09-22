package com.bmveiga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmveiga.repository.DespesaRepository;

@RestController
@RequestMapping("despesa")
public class DespesaController {

	@Autowired
	private DespesaRepository repository;
	
	@GetMapping
	public String teste() {
		return "funciona";
	}
}
