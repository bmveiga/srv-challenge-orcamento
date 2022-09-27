package com.bmveiga.model;

import java.util.List;

public class Resumo {

	private Double receitas;
	private Double despesas;
	private Double saldo;
	private List<GastoCategoria> gastoCategoria;

	public Resumo(Double receitas, Double despesas, List<GastoCategoria> gastoCategoria) {
		this.receitas = receitas;
		this.despesas = despesas;
		this.saldo = getSaldo();
		this.gastoCategoria = gastoCategoria;
	}

	public Double getReceitas() {
		return receitas;
	}

	public void setReceitas(Double receitas) {
		this.receitas = receitas;
	}

	public Double getDespesas() {
		return despesas;
	}

	public void setDespesas(Double despesas) {
		this.despesas = despesas;
	}

	public List<GastoCategoria> getGastoCategoria() {
		return gastoCategoria;
	}

	public void setGastoCategoria(List<GastoCategoria> gastoCategoria) {
		this.gastoCategoria = gastoCategoria;
	}

	public Double getSaldo() {
		return this.receitas - this.despesas;
	}
	
}
