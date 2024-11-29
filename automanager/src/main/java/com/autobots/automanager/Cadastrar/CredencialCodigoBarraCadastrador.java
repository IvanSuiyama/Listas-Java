package com.autobots.automanager.Cadastrar;

import java.util.Date;
import java.util.List;

import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.CredencialCodigoBarra;

public class CredencialCodigoBarraCadastrador {

	public void cadastrar(Usuario usuario, CredencialCodigoBarra credencial) {
		
		if (credencial != null) {

			CredencialCodigoBarra novoCredencial = new CredencialCodigoBarra();

			novoCredencial.setCriacao(new Date());

			novoCredencial.setUltimoAcesso(new Date());


			novoCredencial.setInativo(false);

			novoCredencial.setCodigo(credencial.getCodigo());

			usuario.getCredenciais().add(novoCredencial);

		}
	}

	public void cadastrar(Usuario usuario, List<CredencialCodigoBarra> credenciais) {

		for (CredencialCodigoBarra credencial : credenciais) {

			cadastrar(usuario, credencial);

		}
	}
}
