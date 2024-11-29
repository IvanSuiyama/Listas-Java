package com.autobots.automanager.Selecionar;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entitades.Documento;

@Component
public class DocumentoSelecionador {

    public Documento selecionar(List<Documento> documentos, long id) {
		
        for (Documento documento : documentos) {

            if (documento.getId() == id) {

                return documento; // Return the document as soon as it's found

            }

        }

        return null; // Return null if no document is found

    }
}
