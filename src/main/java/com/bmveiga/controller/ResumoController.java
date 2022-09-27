package com.bmveiga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmveiga.model.Resumo;
import com.bmveiga.service.ResumoService;

@RestController
@RequestMapping("resumo")
public class ResumoController {

	@Autowired
	private ResumoService resumoService;
	
	@GetMapping("/{ano}/{mes}")
	public Resumo buscarResumo(@PathVariable int ano, @PathVariable int mes) {
		return resumoService.resumoMes(ano, mes);
	}
	
}
