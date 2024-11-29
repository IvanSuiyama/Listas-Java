package com.autobots.automanager.Excluir;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.repositorios.UsuarioRepositorio;

@Service
public class VeiculoExcluidor {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void excluir(Usuario usuario, Veiculo veiculo) {
		
        if (usuario != null && veiculo != null) {

            // Remove the vehicle from the user's vehicle list
            usuario.getVeiculos().remove(veiculo);

            usuarioRepositorio.save(usuario);


            // If the vehicle has any associated sales, set the vehicle to null in those sales
            if (veiculo.getVendas() != null) {

                for (Venda venda : veiculo.getVendas()) {

                    venda.setVeiculo(null);

                }
            }
        }
    }

    public void excluir(Usuario usuario, List<Veiculo> veiculos) {

        if (usuario != null && veiculos != null) {

            for (Veiculo veiculoExcluido : veiculos) {

                if (veiculoExcluido != null && veiculoExcluido.getId() != null) {

                    excluir(usuario, veiculoExcluido);

                }
            }
        }
    }
}
