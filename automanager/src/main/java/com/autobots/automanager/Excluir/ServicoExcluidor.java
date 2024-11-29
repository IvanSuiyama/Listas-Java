package com.autobots.automanager.Excluir;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.repositorios.ServicoRepositorio;
import com.autobots.automanager.repositorios.VendaRepositorio;

@Service
public class ServicoExcluidor {

    @Autowired
    private VendaRepositorio vendaRepositorio;
    
    @Autowired
    private ServicoRepositorio servicoRepositorio;

    public void excluir(Empresa empresa, Servico servico) {
		
        if (empresa != null && servico != null) {

            // Remove service from the company's list
            empresa.getServicos().remove(servico);

            // Remove service from all relevant sales
            List<Venda> vendas = vendaRepositorio.findAll();

            for (Venda venda : vendas) {

                venda.getServicos().removeIf(svc -> svc != null && svc.getId().equals(servico.getId()));

            }

            // Delete the service from the repository
            servicoRepositorio.delete(servico);

        }
    }

    public void excluir(Empresa empresa, List<Servico> servicos) {

        if (empresa != null && servicos != null) {

            for (Servico servicoExcluido : servicos) {

                if (servicoExcluido != null && servicoExcluido.getId() != null) {

                    excluir(empresa, servicoExcluido);

                }
            }
        }
    }
}
