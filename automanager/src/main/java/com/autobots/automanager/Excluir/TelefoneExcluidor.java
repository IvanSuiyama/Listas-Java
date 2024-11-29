package com.autobots.automanager.Excluir;
import java.util.List;

import com.autobots.automanager.entitades.Telefone;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelefoneExcluidor {

    private static final Logger logger = LoggerFactory.getLogger(TelefoneExcluidor.class);
	
    private final StringVerificadorNulo verificador = new StringVerificadorNulo();


    public boolean excluir(Usuario usuario, Telefone telefone) {

        if (usuario == null || telefone == null) {

            logger.warn("Usuário ou telefone é nulo. Exclusão não realizada.");

            return false;

        }

        // Verifica se o DDD e o número não são nulos ou vazios
        if (!verificador.verificar(telefone.getDdd()) && !verificador.verificar(telefone.getNumero())) {

            if (usuario.getTelefones().remove(telefone)) {

                logger.info("Telefone (DDD: {}, Número: {}) excluído com sucesso do usuário {}", telefone.getDdd(), telefone.getNumero(), usuario.getId());

                return true;

            } else {

                logger.warn("Telefone (DDD: {}, Número: {}) não encontrado na lista de telefones do usuário {}", telefone.getDdd(), telefone.getNumero(), usuario.getId());

            }

        } else {

            logger.warn("Telefone com DDD ou número inválido. Exclusão não realizada.");

        }

        return false;

    }

    public void excluir(Usuario usuario, List<Telefone> telefones) {

        if (usuario == null || telefones == null) {

            logger.warn("Usuário ou lista de telefones é nula. Exclusão não realizada.");

            return;

        }

        for (Telefone telefoneExcluido : telefones) {

            if (telefoneExcluido.getId() != null) {

                excluir(usuario, telefoneExcluido);

            }
        }
    }
}
