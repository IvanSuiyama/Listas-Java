package com.autobots.automanager.modelos;


import com.autobots.automanager.controles.DocumentoController;
import com.autobots.automanager.entidades.Documento;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionarLinkDocumento implements AdicionadorLink<Documento> {

    @Override
    public void adicionarLink(List<Documento> lista) {

        for (Documento documento : lista) {

            long id = documento.getId();

            Link linkProprio = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(DocumentoController.class).obterDocumento(id)).withSelfRel();

            documento.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Documento objeto) {

        Link linkProprio = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(DocumentoController.class).obterDocumento(0)).withRel("documentos");
				
        objeto.add(linkProprio);
    }
}