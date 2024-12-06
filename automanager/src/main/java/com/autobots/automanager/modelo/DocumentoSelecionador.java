package com.autobots.automanager.modelo;
import com.autobots.automanager.entidades.Documento;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DocumentoSelecionador {
    public Documento selecionar(List<Documento> documentos, long id) {
        Documento selecionado = null;
        for (Documento documento : documentos) {
            if (documento.getId() == id) {
                selecionado = documento;
            }
        }
        return selecionado;
    }
}