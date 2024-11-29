package com.autobots.automanager.Excluir;

import java.util.List;

import com.autobots.automanager.entitades.Documento;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component; // Importando a anotação

@Component // Adicionando a anotação para registrar como bean
public class DocumentoExcluidor {

    private static final Logger logger = LoggerFactory.getLogger(DocumentoExcluidor.class);

    private final StringVerificadorNulo verificador = new StringVerificadorNulo();

    public boolean excluir(Usuario usuario, Documento documento) {
        if (usuario == null || documento == null) {
            logger.warn("Usuário ou documento é nulo. Exclusão não realizada.");
            return false;
        }

        if (!verificador.verificar(documento.getNumero())) {
            if (usuario.getDocumentos().remove(documento)) {
                logger.info("Documento {} excluído com sucesso do usuário {}", documento.getNumero(), usuario.getId());
                return true;
            } else {
                logger.warn("Documento {} não encontrado na lista de documentos do usuário {}", documento.getNumero(), usuario.getId());
            }
        }

        return false;
    }

    public void excluir(Usuario usuario, List<Documento> documentos) {
        if (usuario == null || documentos == null) {
            logger.warn("Usuário ou lista de documentos é nula. Exclusão não realizada.");
            return;
        }

        for (Documento documentoExcluido : documentos) {
            if (documentoExcluido.getId() != null && documentoExcluido.getTipo() != null) {
                excluir(usuario, documentoExcluido);
            }
        }
    }
}
