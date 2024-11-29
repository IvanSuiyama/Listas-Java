package com.autobots.automanager.Atualizar;

import java.util.List;
import java.util.Set;

import com.autobots.automanager.entitades.CredencialUsuarioSenha;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class CredencialAtualizadorUsuarioSenha {
	
	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(CredencialUsuarioSenha credencial, CredencialUsuarioSenha atualizacao) {

		if (atualizacao != null && credencial != null) {

			// Update nomeUsuario if it is not null or empty
			if (!verificador.verificar(atualizacao.getNomeUsuario())) {

				credencial.setNomeUsuario(atualizacao.getNomeUsuario());

			}

			// Update senha if it is not null or empty
			if (!verificador.verificar(atualizacao.getSenha())) {

				credencial.setSenha(atualizacao.getSenha());

			}
		}
	}

	public void atualizar(Set<CredencialUsuarioSenha> credenciais, List<CredencialUsuarioSenha> atualizacoes) {

		if (credenciais == null || atualizacoes == null || credenciais.isEmpty() || atualizacoes.isEmpty()) {

			return; // Return early if collections are null or empty

		}

		for (CredencialUsuarioSenha atualizacao : atualizacoes) {

			if (atualizacao.getId() != null) {

				for (CredencialUsuarioSenha credencial : credenciais) {

					// Use equals() to compare Long IDs
					if (atualizacao.getId().equals(credencial.getId())) {

						atualizar(credencial, atualizacao);

						break; // Exit loop once the matching credential is updated

					}
				}
			}
		}
	}
}
