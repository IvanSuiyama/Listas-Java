package com.autobots.automanager.modelo;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentoCadastrar {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void cadastraNovo(Cliente cliente, Documento documento) {
        if (documento != null) {
            if (!verificador.verificar(documento.getTipo()) &&
                !verificador.verificar(documento.getNumero())) {
                Documento documentoNovo = new Documento();
                documentoNovo.setTipo(documento.getTipo());
                documentoNovo.setNumero(documento.getNumero());
                cliente.getDocumentos().add(documentoNovo);
            }
        }
    }

    public void cadastraNovo(Cliente cliente, List<Documento> documentos) {
        for (Documento documento : documentos) {
            cadastraNovo(cliente, documento);
        }
    }

}
