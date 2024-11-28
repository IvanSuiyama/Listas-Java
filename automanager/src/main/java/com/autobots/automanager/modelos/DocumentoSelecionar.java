package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Documento;

@Component
public class DocumentoSelecionar {
	
	public Documento selecionar(List<Documento> documentos, long id) {

		for (Documento documento : documentos) {

			if (documento.getId() == id) {
				return documento; // Retorna diretamente o documento encontrado

			}
		}

		return null; // Retorna null caso não encontre

	}
}