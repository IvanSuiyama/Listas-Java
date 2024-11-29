package com.autobots.automanager.Selecionar;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entitades.Veiculo;

@Component
public class VeiculoSelecionador {
    
    public Veiculo selecionar(List<Veiculo> veiculos, long id) {
		
        for (Veiculo veiculo : veiculos) {

            if (veiculo.getId() == id) {

                return veiculo; // Return the vehicle as soon as it's found

            }

        }

        return null; // Return null if no vehicle is found

    }
}
