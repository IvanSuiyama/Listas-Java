package com.autobots.automanager.Adicionar;


import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.Adicionar.AdicionarLink;
import com.autobots.automanager.controllers.client.EmailControle;
import com.autobots.automanager.entitades.*;

@Component
public class AdicionadorLinkEmail implements AdicionarLink<Email> {

	@Override
	public void adicionarLink(List<Email> lista) {

		for (Email email : lista) {

			long id = email.getId();


			Link linkProprio = WebMvcLinkBuilder

					.linkTo(WebMvcLinkBuilder

							.methodOn(EmailControle.class)

							.obterEmail(id))

					.withSelfRel();

			email.add(linkProprio);

		}
	}

	@Override
	public void adicionarLink(Email objeto) {

		Link linkProprio = WebMvcLinkBuilder

				.linkTo(WebMvcLinkBuilder

						.methodOn(EmailControle.class)

						.obterEmails())

				.withRel("Emails");

		objeto.add(linkProprio);
		
	}
}
