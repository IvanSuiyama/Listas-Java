package com.autobots.automanager.Selecionar;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entitades.Servico;

@Component
public class ServicoSelecionador {
    
    public Servico selecionar(List<Servico> servicos, long id) {
		
        for (Servico servico : servicos) {

            if (servico.getId() == id) {

                return servico; // Return the service as soon as it's found

            }
        }

        return null; // Return null if no service is found

    }
}
