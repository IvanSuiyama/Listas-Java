package com.autobots.automanager.Adicionar;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.Adicionar.AdicionarLink;
import com.autobots.automanager.controllers.CredencialControle;
import com.autobots.automanager.entitades.CredencialCodigoBarra;

@Component
public class AdicionadorLinkCredencialCodigoBarra implements AdicionarLink<CredencialCodigoBarra>{
	
	@Override
	public void adicionarLink(List<CredencialCodigoBarra> lista) {

		for (CredencialCodigoBarra credencial : lista) {

			long id = credencial.getId();

			Link linkProprio = WebMvcLinkBuilder

					.linkTo(WebMvcLinkBuilder

							.methodOn(CredencialControle.class)

							.obterCredencialCodigoBarra(id))

					.withSelfRel();


			credencial.add(linkProprio);

		}
	}

	@Override
	public void adicionarLink(CredencialCodigoBarra objeto) {

		Link linkProprio = WebMvcLinkBuilder

				.linkTo(WebMvcLinkBuilder

						.methodOn(CredencialControle.class)

						.obterCredenciaisCodigoBarra())

				.withRel("credenciais");

		objeto.add(linkProprio);
		
	}
}
