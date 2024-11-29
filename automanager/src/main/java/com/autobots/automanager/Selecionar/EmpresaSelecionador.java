package com.autobots.automanager.Selecionar;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entitades.Empresa;

@Component
public class EmpresaSelecionador {
    
    public Empresa selecionar(List<Empresa> empresas, long id) {

        for (Empresa empresa : empresas) {

            if (empresa.getId() == id) {

                return empresa; // Return the empresa as soon as it's found

            }
        }

        return null; // Return null if no empresa is found
		
    }
}
