package com.autobots.automanager.Adicionar;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.Adicionar.AdicionarLink;
import com.autobots.automanager.controllers.ServicoControle;
import com.autobots.automanager.entitades.Servico;

@Component
public class AdicionadorLinkServico implements AdicionarLink<Servico> {

	@Override
	public void adicionarLink(List<Servico> lista) {

		for (Servico servico : lista) {

			long id = servico.getId();

			if (!servico.hasLink("self")) {		

			Link linkProprio = WebMvcLinkBuilder

					.linkTo(WebMvcLinkBuilder

							.methodOn(ServicoControle.class)

							.obterServico(id))

					.withSelfRel();

			servico.add(linkProprio);

			}
		}
	}

	@Override
	public void adicionarLink(Servico servico) {

		if (!servico.hasLink("servicos")) {

		Link linkProprio = WebMvcLinkBuilder

				.linkTo(WebMvcLinkBuilder

						.methodOn(ServicoControle.class)

						.obterServicos())

				.withRel("servicos");

		servico.add(linkProprio);
		
		}
	}
}