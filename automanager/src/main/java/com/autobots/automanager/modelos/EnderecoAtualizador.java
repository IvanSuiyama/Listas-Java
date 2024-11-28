package com.autobots.automanager.modelos;


import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class EnderecoAtualizador {

	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Endereco endereco, Endereco atualizacao) {

		if (atualizacao != null) {

			// Atualiza os campos do endereço se os novos valores não forem nulos
			if (!verificador.verificar(atualizacao.getEstado())) {
				endereco.setEstado(atualizacao.getEstado());
			}

			if (!verificador.verificar(atualizacao.getCidade())) {
				endereco.setCidade(atualizacao.getCidade());
			}

			if (!verificador.verificar(atualizacao.getBairro())) {
				endereco.setBairro(atualizacao.getBairro());
			}

			if (!verificador.verificar(atualizacao.getRua())) {
				endereco.setRua(atualizacao.getRua());
			}

			if (!verificador.verificar(atualizacao.getNumero())) {
				endereco.setNumero(atualizacao.getNumero());
			}

			if (!verificador.verificar(atualizacao.getInformacoesAdicionais())) {
				endereco.setInformacoesAdicionais(atualizacao.getInformacoesAdicionais());
			}

		}
	}
}