package com.autobots.automanager.Adicionar;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.add.AdicionadorLink;
import com.autobots.automanager.controllers.CredencialControle;
import com.autobots.automanager.entitades.*;

@Component
public class AdicionarLinklUsuarioSenha implements AdicionarLink<UsuarioSenha> {

	@Override
	public void adicionarLink(List<UsuarioSenha> lista) {

		for (UsuarioSenha credencial : lista) {

			long id = credencial.getId();

			Link linkProprio = WebMvcLinkBuilder

					.linkTo(WebMvcLinkBuilder

							.methodOn(CredencialControle.class)

							.obterCredencialUsuarioSenha(id))

					.withSelfRel();

			credencial.add(linkProprio);

		}
	}

	@Override
	public void adicionarLink(UsuarioSenha objeto) {

		Link linkProprio = WebMvcLinkBuilder

				.linkTo(WebMvcLinkBuilder

						.methodOn(CredencialControle.class)

						.obterCredenciaisUsuarioSenha())

				.withRel("credenciais");
				
		objeto.add(linkProprio);
	}
}
