package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Telefone;

@Component
public class TelefoneSeleciona {
    
    public Telefone selecionar(List<Telefone> telefones, long id) {

        for (Telefone telefone : telefones) {

            if (telefone.getId() == id) {

                return telefone; // Retorna imediatamente o telefone selecionado

            }
        }

        return null; // Retorna null se nenhum telefone for encontrado
    }
}
