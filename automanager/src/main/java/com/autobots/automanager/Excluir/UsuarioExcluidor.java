package com.autobots.automanager.Excluir;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.Excluir.CredencialExcluidor;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.repositorios.UsuarioRepositorio;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.repositorios.VendaRepositorio;

@Service
public class UsuarioExcluidor {

    private final DocumentoExcluidor documentoExcluidor = new DocumentoExcluidor();

    private final EmailExcluidor emailExcluidor = new EmailExcluidor();

    private final CredencialExcluidor credencialExcluidor = new CredencialExcluidor();

    private final TelefoneExcluidor telefoneExcluidor = new TelefoneExcluidor();

    @Autowired
    private EmpresaRepositorio empresaRepositorio;

    @Autowired
    private UsuarioRepositorio repositorio;

    @Autowired
    private VendaRepositorio vendaRepositorio;

    public void excluir(Usuario usuario) {

        if (usuario == null) {

            return; // Exit if the user is null

        }

        // Nullify references in `Venda`
        List<Venda> vendas = vendaRepositorio.findAll();

        for (Venda venda : vendas) {

            if (venda.getCliente() != null && venda.getCliente().getId().equals(usuario.getId())) {

                venda.setCliente(null);

                vendaRepositorio.save(venda);

            }

            if (venda.getFuncionario() != null && venda.getFuncionario().getId().equals(usuario.getId())) {

                venda.setFuncionario(null);

                vendaRepositorio.save(venda);

            }

        }

        // Remove associated documents, emails, and credentials
        documentoExcluidor.excluir(usuario, usuario.getDocumentos().stream().collect(Collectors.toList()));

        emailExcluidor.excluir(usuario, usuario.getEmails().stream().collect(Collectors.toList()));

        credencialExcluidor.excluir(usuario, usuario.getCredenciais().stream().collect(Collectors.toList()));

        usuario.getCredenciais().clear();

        telefoneExcluidor.excluir(usuario, usuario.getTelefones().stream().collect(Collectors.toList()));

        // Unassign vehicles
        for (Veiculo veiculo : usuario.getVeiculos()) {

            veiculo.setProprietario(null);
			
        }

        // Remove user from all associated companies
        List<Empresa> empresas = empresaRepositorio.findAll();

        empresas.forEach(empresa -> 

            empresa.getUsuarios().removeIf(user -> user.getId().equals(usuario.getId()))

        );

        // Finally, delete the user
        repositorio.delete(usuario);

    }

}
