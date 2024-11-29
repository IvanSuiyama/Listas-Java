package com.autobots.automanager.Adicionar;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.Adicionar.AdicionarLink;
import com.autobots.automanager.controllers.client.TelefoneControle;
import com.autobots.automanager.entitades.Telefone;

@Component
public class AdicionadorLinkTelefone implements AdicionarLink<Telefone> {

	@Override
	public void adicionarLink(List<Telefone> lista) {

		for (Telefone telefone : lista) {

			long id = telefone.getId();

			Link linkProprio = WebMvcLinkBuilder

					.linkTo(WebMvcLinkBuilder

							.methodOn(TelefoneControle.class)

							.obterTelefone(id))

					.withSelfRel();

			telefone.add(linkProprio);

		}
	}

	@Override
	public void adicionarLink(Telefone objeto) {

		Link linkProprio = WebMvcLinkBuilder

				.linkTo(WebMvcLinkBuilder

						.methodOn(TelefoneControle.class)

						.obterTelefones())

				.withRel("telefones");

		objeto.add(linkProprio);
		
	}
}