package com.autobots.automanager.Adicionar;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.Adicionar.AdicionarLink;
import com.autobots.automanager.controllers.MercadoriaControle;
import com.autobots.automanager.entitades.Mercadoria;

@Component
public class AdicionadorLinkMercadoria implements AdicionarLink<Mercadoria> {

	@Override
	public void adicionarLink(List<Mercadoria> lista) {

		for (Mercadoria mercadoria : lista) {

			long id = mercadoria.getId();

			if (!mercadoria.hasLink("self")) {		

			Link linkProprio = WebMvcLinkBuilder

					.linkTo(WebMvcLinkBuilder

							.methodOn(MercadoriaControle.class)

							.obterMercadoria(id))

					.withSelfRel();

			mercadoria.add(linkProprio);

			}
		}
	}

	@Override
	public void adicionarLink(Mercadoria mercadoria) {

		if (!mercadoria.hasLink("mercadorias")) {	

		Link linkProprio = WebMvcLinkBuilder

				.linkTo(WebMvcLinkBuilder

						.methodOn(MercadoriaControle.class)

						.obterMercadorias())

				.withRel("mercadorias");

		mercadoria.add(linkProprio);
		
		}
	}
}