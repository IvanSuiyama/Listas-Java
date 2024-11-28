package com.autobots.automanager.modelos;

import java.util.List;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelos.StringVerificadorNulo;

public class TelefoneCadastrar {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void cadastrar(Cliente cliente, Telefone telefone) {

        if (telefone != null) {

            Telefone novoTelefone = new Telefone();

            if (!verificador.verificar(telefone.getDdd())) {
                novoTelefone.setDdd(telefone.getDdd());
            }

            if (!verificador.verificar(telefone.getNumero())) {
                novoTelefone.setNumero(telefone.getNumero());

            }

            cliente.getTelefones().add(novoTelefone);

        }
    }

    public void cadastrar(Cliente cliente, List<Telefone> telefones) {

        if (telefones != null) {
            telefones.forEach(telefone -> cadastrar(cliente, telefone));

        }

    }
}
