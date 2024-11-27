package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private ClienteSelecionador clienteSelecionador;
    @Autowired
    private EnderecoAtualizador enderecoAtualizador;

     @GetMapping("/endereco/{id}")
    public Endereco visualizarEndereco(@PathVariable long id){
        List<Cliente> clientes = clienteRepositorio.findAll();
        return clienteSelecionador.selecionar(clientes, id).getEndereco();
    }

    @PutMapping("/atualizar/{id}")
    public void  atualizarEndereco(@RequestBody Endereco endereco, @PathVariable long id){
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, id);
        enderecoAtualizador.atualizar(cliente.getEndereco(), endereco);
        clienteRepositorio.save(cliente);
    }


}
