package com.autobots.automanager.modelo;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TelefoneCadastra {
    private StringVerificadorNulo verificadorNulo = new StringVerificadorNulo();

    public void cadastraNovo(Cliente cliente, Telefone telefone) {
        if (telefone != null) {
            if (!verificadorNulo.verificar(telefone.getDdd()) &&
                !verificadorNulo.verificar(telefone.getNumero())) {
                Telefone telefoneCadastrado = new Telefone();
                telefoneCadastrado.setDdd(telefone.getDdd());
                telefoneCadastrado.setNumero(telefone.getNumero());
                cliente.getTelefones().add(telefoneCadastrado);
            }
        }
    }

    public void cadastraNovo(Cliente cliente, List<Telefone> telefones) {
        for (Telefone telefone : telefones) {
            cadastraNovo(cliente, telefone);
        }
    }
}
