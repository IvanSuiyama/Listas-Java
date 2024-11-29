package com.autobots.automanager.Excluir;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.repositorios.MercadoriaRepositorio;
import com.autobots.automanager.repositorios.VendaRepositorio;

@Service
public class MercadoriaExcluidor {

    @Autowired
    private VendaRepositorio vendaRepositorio;
    
    @Autowired
    private MercadoriaRepositorio mercadoriaRepositorio;

    public void excluir(Empresa empresa, Mercadoria mercadoria) {

        if (empresa != null && mercadoria != null) {

            // Remove mercadoria from the company's inventory
            empresa.getMercadorias().remove(mercadoria);

            // Remove mercadoria from all sales where it's included
            List<Venda> vendas = vendaRepositorio.findAll();
			
            for (Venda venda : vendas) {

                venda.getMercadorias().removeIf(merc -> merc != null && merc.getId().equals(mercadoria.getId()));

            }

            // Delete the mercadoria from the repository
            mercadoriaRepositorio.delete(mercadoria);

        }
    }

    public void excluir(Empresa empresa, List<Mercadoria> mercadorias) {

        if (empresa != null && mercadorias != null) {

            for (Mercadoria mercadoriaExcluida : mercadorias) {

                if (mercadoriaExcluida != null && mercadoriaExcluida.getId() != null) {

                    excluir(empresa, mercadoriaExcluida);
					
                }
            }
        }
    }
}
