package com.autobots.automanager.Atualizar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.EmpresaDto;
import com.autobots.automanager.Atualizar.EnderecoAtualizador;

@Component
public class EmpresaAtualizador {
    
    private final StringVerificadorNulo verificador = new StringVerificadorNulo();
    
    @Autowired
    private EnderecoAtualizador enderecoAtualizador;

    private void atualizarDados(Empresa empresa, EmpresaDto atualizacao) {
		
        if (atualizacao == null) {

            return; // Avoid null pointer exception

        }

        if (!verificador.verificar(atualizacao.getNomeFantasia())) {

            empresa.setNomeFantasia(atualizacao.getNomeFantasia());

        }
        
        if (!verificador.verificar(atualizacao.getRazaoSocial())) {

            empresa.setRazaoSocial(atualizacao.getRazaoSocial());

        }
    }

    public void atualizar(Empresa empresa, EmpresaDto atualizacao) {

        if (empresa == null || atualizacao == null) {

            return; // Avoid null pointer exception

        }

        atualizarDados(empresa, atualizacao);
        
        if (empresa.getEndereco() != null && atualizacao.getEndereco() != null) {

            enderecoAtualizador.atualizar(empresa.getEndereco(), atualizacao.getEndereco());

        }
    }
}