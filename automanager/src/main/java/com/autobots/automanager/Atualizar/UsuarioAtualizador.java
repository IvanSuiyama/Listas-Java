package com.autobots.automanager.Atualizar;

import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.UsuarioDto;

@Service
public class UsuarioAtualizador {
	
	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	private void atualizarDados(Usuario usuario, UsuarioDto usuarioDto) {

		if (usuarioDto == null) {

			return; // Early exit if usuarioDto is null

		}

		if (!verificador.verificar(usuarioDto.getNome())) {

			usuario.setNome(usuarioDto.getNome());

		}

		if (!verificador.verificar(usuarioDto.getNomeSocial())) {

			usuario.setNomeSocial(usuarioDto.getNomeSocial());

		}

		if (usuarioDto.getPerfis() != null) {

			usuario.setPerfis(usuarioDto.getPerfis());

		}
	}

	public void atualizar(Usuario usuario, UsuarioDto usuarioDto) {

		if (usuarioDto != null) { // Null check for usuarioDto

			atualizarDados(usuario, usuarioDto);

		}
	}
}
