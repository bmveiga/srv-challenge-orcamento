package com.bmveiga.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmveiga.enums.Categoria;
import com.bmveiga.model.Despesa;
import com.bmveiga.model.GastoCategoria;
import com.bmveiga.model.Receita;
import com.bmveiga.model.Resumo;

@Service
public class ResumoService {
	
	@Autowired
	DespesaService despesaService;
	
	@Autowired
	ReceitaService receitaService;
	
	public Resumo resumoMes(int ano, int mes) {
		List<Despesa> despesasMes = despesaService.buscarPorMes(mes, ano);
		List<Receita> receitasMes = receitaService.buscarPorMes(mes, ano);
		
		List<GastoCategoria> gastosCategoria = new ArrayList<>();
		
		Double valorDespesas = 0.0;
		Double valorReceitas = 0.0;
		
		for (Despesa despesa : despesasMes) {
			Double valor = despesa.getValor();
			valorDespesas = valorDespesas + valor;
			Categoria categoria = despesa.getCategoria();
			gastosCategoria = separarGastosCategoria(valor, categoria, gastosCategoria);
		}
		
		for (Receita receita : receitasMes) {
			valorReceitas = valorReceitas + receita.getValor();
		}
		
		Resumo resumo = new Resumo(valorReceitas, valorDespesas, gastosCategoria);
		return resumo;
	}

	private List<GastoCategoria> separarGastosCategoria(Double valor, Categoria categoria, List<GastoCategoria> gastosCategoria) {
		if(gastosCategoria.indexOf(categoria) != -1) {
			for (GastoCategoria gasto : gastosCategoria) {
				if(gasto.getCategoria().equals(categoria)) {
					Double valorAtualizado = gasto.getValor() + valor;
					gasto.setValor(valorAtualizado);
				}
			}
		} else {
			gastosCategoria.add(new GastoCategoria(categoria, valor));
		}
		return gastosCategoria;
		
	}

	
	
}
