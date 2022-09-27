package com.bmveiga.model;

import java.util.Objects;

import com.bmveiga.enums.Categoria;

public class GastoCategoria {

	private Categoria categoria;
	private Double valor;

	public GastoCategoria() {
	}

	public GastoCategoria(Categoria categoria, Double valor) {
		this.categoria = categoria;
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GastoCategoria other = (GastoCategoria) obj;
		return categoria == other.categoria;
	}

}
