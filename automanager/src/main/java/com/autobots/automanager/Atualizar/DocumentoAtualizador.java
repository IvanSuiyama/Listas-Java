package com.autobots.automanager.Atualizar;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component; // Importando a anotação

import com.autobots.automanager.entitades.Documento;
import com.autobots.automanager.modelos.StringVerificadorNulo;

@Component // Adicionando a anotação para registrar como bean
public class DocumentoAtualizador {
    
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Documento documento, Documento atualizacao) {
        if (atualizacao != null && documento != null) {
            // Atualiza o tipo se não for nulo ou vazio
            if (!verificador.verificar(atualizacao.getTipo().toString())) {
                documento.setTipo(atualizacao.getTipo());
            }
            // Atualiza o número se não for nulo ou vazio
            if (!verificador.verificar(atualizacao.getNumero())) {
                documento.setNumero(atualizacao.getNumero());
            }
        }
    }

    public void atualizar(Set<Documento> documentos, List<Documento> atualizacoes) {
        if (documentos == null || atualizacoes == null || documentos.isEmpty() || atualizacoes.isEmpty()) {
            return; // Retorna se as coleções forem nulas ou vazias
        }

        for (Documento atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                for (Documento documento : documentos) {
                    // Compara IDs
                    if (atualizacao.getId().equals(documento.getId())) {
                        atualizar(documento, atualizacao);
                        break; // Sai do loop após atualizar o documento correspondente
                    }
                }
            }
        }
    }
}
