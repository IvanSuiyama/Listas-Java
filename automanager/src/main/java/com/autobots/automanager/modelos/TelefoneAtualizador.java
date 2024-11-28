package com.autobots.automanager.modelos;

import java.util.List;

import com.autobots.automanager.entidades.Telefone;

public class TelefoneAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Telefone telefone, Telefone atualizacao) {

        if (atualizacao != null) {

            if (!verificador.verificar(atualizacao.getDdd())) {
                telefone.setDdd(atualizacao.getDdd());
            }

            if (!verificador.verificar(atualizacao.getNumero())) {
                telefone.setNumero(atualizacao.getNumero());
            }

        }
    }

    public void atualizar(List<Telefone> telefones, List<Telefone> atualizacoes) {

        for (Telefone atualizacao : atualizacoes) {

            Long idAtualizacao = atualizacao.getId();

            if (idAtualizacao != null) {

                // Encontra o telefone correspondente e atualiza
                telefones.stream()
                         .filter(telefone -> telefone.getId().equals(idAtualizacao))
                         .findFirst()
                         .ifPresent(telefone -> atualizar(telefone, atualizacao));

            }
        }
    }
}