package com.autobots.automanager.Atualizar;

import java.util.List;
import java.util.Set;

import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class MercadoriaAtualizador {
	
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Mercadoria mercadoria, Mercadoria atualizacao) {

        if (atualizacao == null || mercadoria == null) {

            return; // Avoid NullPointerExceptions

        }

        if (!verificador.verificar(atualizacao.getNome())) {

            mercadoria.setNome(atualizacao.getNome());

        }
        if (!verificador.verificar(atualizacao.getDescricao())) {

            mercadoria.setDescricao(atualizacao.getDescricao());
        }

        mercadoria.setCadastro(atualizacao.getCadastro());

        mercadoria.setValidade(atualizacao.getValidade());

        mercadoria.setFabricao(atualizacao.getFabricao());

        mercadoria.setQuantidade(atualizacao.getQuantidade());

        mercadoria.setValor(atualizacao.getValor());

    }

    public void atualizar(Set<Mercadoria> mercadorias, List<Mercadoria> atualizacoes) {

        if (mercadorias == null || atualizacoes == null || mercadorias.isEmpty() || atualizacoes.isEmpty()) {

            return; // Avoid unnecessary processing if null or empty

        }

        for (Mercadoria atualizacao : atualizacoes) {

            if (atualizacao.getId() != null) {

                for (Mercadoria mercadoria : mercadorias) {

                    if (atualizacao.getId().equals(mercadoria.getId())) { 

                        atualizar(mercadoria, atualizacao);

                        break; // Break after finding the matching mercadoria

                    }
                }
            }
        }
    }
}
