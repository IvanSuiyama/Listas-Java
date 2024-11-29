package com.autobots.automanager.Selecionar;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entitades.Venda;

@Component
public class VendaSelecionador {

    public Venda selecionar(List<Venda> vendas, long id) {

        for (Venda venda : vendas) {

            if (venda.getId() == id) {

                return venda; // Return the sale as soon as it's found

            }
        }

        return null; // Return null if no sale is found
		
    }
}
