package com.autobots.automanager.modelo;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Documento;

import java.util.List;

@Component
public class DocumentoAltera {
    private StringVerificadorNulo verificadorNulo = new StringVerificadorNulo();
    
    public void atualiza(Documento documento, Documento atualizado) {
        if (atualizado != null) {
            if (verificadorNulo.verificar(atualizado.getTipo())){
                documento.setTipo(atualizado.getTipo());
            }
            if (verificadorNulo.verificar(atualizado.getNumero())){
                documento.setNumero(atualizado.getNumero());
            }
        }

    }

    public void atualiza(List<Documento> documentos, List<Documento> atualizados) {
        for (Documento atualizado : atualizados) {
            for (Documento documento: documentos) {
                if (atualizado.getId() != null) {
                    if (atualizado.getId() == documento.getId() ) {
                        atualiza(documento, atualizado);
                    } 
                }
            }
        }
    }
}
