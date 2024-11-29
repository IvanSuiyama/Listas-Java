package com.autobots.automanager.Atualizar;

import java.util.List;
import java.util.Set;

import com.autobots.automanager.entitades.Telefone;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class TelefoneAtualizador {
	
	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Telefone telefone, Telefone atualizacao) {

		if (telefone == null || atualizacao == null) {

			return; // Early exit if either telefone or atualizacao is null

		}

		if (!verificador.verificar(atualizacao.getDdd())) {

			telefone.setDdd(atualizacao.getDdd());

		}

		if (!verificador.verificar(atualizacao.getNumero())) {

			telefone.setNumero(atualizacao.getNumero());

		}

	}

	public void atualizar(Set<Telefone> telefones, List<Telefone> atualizacoes) {

		for (Telefone atualizacao : atualizacoes) {

			if (atualizacao.getId() != null) {

				telefones.stream()

					.filter(telefone -> telefone.getId().equals(atualizacao.getId()))

					.forEach(telefone -> atualizar(telefone, atualizacao));

			}
		}
	}
}
