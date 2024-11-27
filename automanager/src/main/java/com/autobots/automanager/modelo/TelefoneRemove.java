package com.autobots.automanager.modelo;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;

import java.util.List;

@Component
public class TelefoneRemove {
    private StringVerificadorNulo verificadorNulo = new StringVerificadorNulo();

    public void deletar(Cliente cliente, Telefone telefone) {
        if (telefone != null) {
            if (!verificadorNulo.verificar(telefone.getDdd()) && !verificadorNulo.verificar(telefone.getNumero())) {
                cliente.getTelefones().remove(telefone);
            }
        }
    }

    public void deletar(Cliente cliente, List<Telefone> telefones) {
        for (Telefone telefoneExcluido : telefones) {
            if (telefoneExcluido.getId() != null) {
                deletar(cliente, telefoneExcluido);
            }
        }
    }
}
