package com.autobots.automanager.Selecionar;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entitades.Usuario;

@Component
public class UsuarioSelecionador {
    
    public Usuario selecionar(List<Usuario> usuarios, long id) {
		
        for (Usuario usuario : usuarios) {

            if (usuario.getId() == id) {

                return usuario; // Return the usuario as soon as it's found

            }

        }
        return null; // Return null if no usuario is found

    }
}
