package com.autobots.automanager.modelo;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;

import java.util.List;

@Component
public class DocumentoExclui {
    private StringVerificadorNulo verificadorNulo = new StringVerificadorNulo();

    public void deletar(Cliente cliente, Documento documento) {
        if (documento != null) {
            if (!verificadorNulo.verificar(documento.getTipo()) && !verificadorNulo.verificar(documento.getNumero())) {
                cliente.getDocumentos().remove(documento);
            }
        }
    }

    public void deletar(Cliente cliente, List<Documento> documentos) {
        for (Documento documentoDeletado : documentos) {
            if (documentoDeletado.getId() != null) {
                deletar(cliente, documentoDeletado);
            }
        }
    }
}
