package com.autobots.automanager.Excluir;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.modelos.VendaDto;
import com.autobots.automanager.repositorios.UsuarioRepositorio;
import com.autobots.automanager.repositorios.VendaRepositorio;

@Service
public class VendaExcluidor {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private VendaRepositorio vendaRepositorio;

    public void excluir(Set<Venda> vendas, VendaDto vendaDto) {
		
        if (vendaDto != null && vendaDto.getId() != null) {

            Optional<Venda> optionalVenda = vendaRepositorio.findById(vendaDto.getId());

            if (optionalVenda.isPresent()) {

                Venda venda = optionalVenda.get();

                // Remove venda from the client, if applicable
                if (venda.getCliente() != null) {

                    Usuario cliente = venda.getCliente();

                    cliente.getVendas().remove(venda);

                    usuarioRepositorio.save(cliente);

                }

                // Remove venda from the employee (funcionario), if applicable
                if (venda.getFuncionario() != null) {

                    Usuario funcionario = venda.getFuncionario();

                    funcionario.getVendas().remove(venda);

                    usuarioRepositorio.save(funcionario);

                }

                // Remove venda from the vehicle, if applicable
                if (venda.getVeiculo() != null) {

                    venda.getVeiculo().getVendas().remove(venda);

                }

                // Remove venda from the set and repository
                vendas.remove(venda);

                vendaRepositorio.delete(venda);

            }
        }
    }

    public void excluir(Set<Venda> vendas, List<VendaDto> vendasExcluidas) {

        for (VendaDto vendaExcluida : vendasExcluidas) {

            if (vendaExcluida.getId() != null) {

                excluir(vendas, vendaExcluida);

            }
        }
    }
}
