package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Endereco;

@Component
public class EnderecoSeleciona {

	public Endereco selecionar(List<Endereco> enderecos, long id) {

		for (Endereco endereco : enderecos) {

			if (endereco.getId() == id) {
				return endereco; // Retorna imediatamente ao encontrar o endereço

			}
		}

		return null; // Retorna null se nenhum endereço for encontrado

	}
}
