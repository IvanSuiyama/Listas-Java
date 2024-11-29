package com.autobots.automanager.Selecionar;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entitades.Mercadoria;

@Component
public class MercadoriaSelecionador {
    
    public Mercadoria selecionar(List<Mercadoria> mercadorias, long id) {
		
        for (Mercadoria mercadoria : mercadorias) {

            if (mercadoria.getId() == id) {

                return mercadoria; // Return the mercadoria as soon as it's found

            }
        }

        return null; // Return null if no mercadoria is found

    }
}
