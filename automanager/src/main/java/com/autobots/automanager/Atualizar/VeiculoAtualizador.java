package com.autobots.automanager.Atualizar;

import java.util.List;
import java.util.Set;

import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.VeiculoDto;

public class VeiculoAtualizador {
	
	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Veiculo veiculo, VeiculoDto atualizacao) {

		if (atualizacao == null || veiculo == null) {

			return; // Ensure no null values are processed

		}
		
		if (!verificador.verificar(atualizacao.getModelo())) {

			veiculo.setModelo(atualizacao.getModelo());

		}

		if (!verificador.verificar(atualizacao.getPlaca())) {

			veiculo.setPlaca(atualizacao.getPlaca());

		}

		veiculo.setTipo(atualizacao.getTipo());

	}

	public void atualizar(Set<Veiculo> veiculos, List<VeiculoDto> atualizacoes) {

		if (veiculos == null || atualizacoes == null || veiculos.isEmpty() || atualizacoes.isEmpty()) {

			return; // Avoid unnecessary processing

		}
		
		for (VeiculoDto atualizacao : atualizacoes) {

			if (atualizacao.getId() != null) {

				for (Veiculo veiculo : veiculos) {

					if (atualizacao.getId().equals(veiculo.getId())) { // Use equals for Long type comparison

						atualizar(veiculo, atualizacao);

						break; // Exit loop after updating the correct vehicle
						
					}
				}
			}
		}
	}
}
