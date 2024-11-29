package com.autobots.automanager.Atualizar;

import java.util.List;
import java.util.Set;

import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class ServicoAtualizador {

	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Servico servico, Servico atualizacao) {

		if (atualizacao == null || servico == null) {

			return; // Avoid NullPointerExceptions

		}

		if (!verificador.verificar(atualizacao.getNome())) {

			servico.setNome(atualizacao.getNome());

		}
		if (!verificador.verificar(atualizacao.getDescricao())) {

			servico.setDescricao(atualizacao.getDescricao());

		}

		servico.setValor(atualizacao.getValor());

	}

	public void atualizar(Set<Servico> servicos, List<Servico> atualizacoes) {

		if (servicos == null || atualizacoes == null || servicos.isEmpty() || atualizacoes.isEmpty()) {

			return; // Avoid unnecessary processing if null or empty

		}

		for (Servico atualizacao : atualizacoes) {

			if (atualizacao.getId() != null) {

				for (Servico servico : servicos) {

					if (atualizacao.getId().equals(servico.getId())) { 

						atualizar(servico, atualizacao);

						break; // Break after finding the matching servico
						
					}
				}
			}
		}
	}
}
