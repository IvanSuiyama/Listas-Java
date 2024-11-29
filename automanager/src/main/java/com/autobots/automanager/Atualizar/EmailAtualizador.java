package com.autobots.automanager.Atualizar;

import java.util.List;
import java.util.Set;

import com.autobots.automanager.entitades.Email;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class EmailAtualizador {
	
	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Email email, Email atualizacao) {

		if (atualizacao != null && email != null) {

			if (!verificador.verificar(atualizacao.getEndereco())) {

				email.setEndereco(atualizacao.getEndereco());

			}
		}
	}

	public void atualizar(Set<Email> emails, List<Email> atualizacoes) {

		if (emails == null || atualizacoes == null || emails.isEmpty() || atualizacoes.isEmpty()) {

			return; // Early return if collections are null or empty

		}

		for (Email atualizacao : atualizacoes) {

			if (atualizacao.getId() != null) {

				for (Email email : emails) {

					// Use equals() to compare Long IDs
					if (atualizacao.getId().equals(email.getId())) {

						atualizar(email, atualizacao);

						break; // Exit loop once the matching email is updated

					}
				}
			}
		}
	}
}
