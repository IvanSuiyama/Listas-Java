package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.modelo.EnderecoSelecionador;
import com.autobots.automanager.repositorios.EnderecoRepositorio;


import java.util.List;
@RestController
@RequestMapping("/endereco")
public class EnderecoControle {

    @Autowired
    private EnderecoRepositorio repositorio;

    @Autowired
    private EnderecoSelecionador selecionador;

    @GetMapping("/endereco/{id}")
    public Endereco obterEndereco(@PathVariable long id) {
        List<Endereco> enderecos = repositorio.findAll();
        return selecionador.selecionar(enderecos, id);
    }

    @GetMapping("/enderecos")
    public List<Endereco> obterEnderecos() {
        List<Endereco> enderecos = repositorio.findAll();
        return enderecos;
    }

    @PutMapping("/atualizar")
    public void atualizarEndereco(@RequestBody Endereco atualizacao) {
        Endereco endereco = repositorio.getById(atualizacao.getId());
        EnderecoAtualizador atualizador = new EnderecoAtualizador();
        atualizador.atualizar(endereco, atualizacao);
        repositorio.save(endereco);
    }

    @DeleteMapping("/excluir")
    public void excluirEndereco(@RequestBody Endereco exclusao) {
        Endereco endereco = repositorio.getById(exclusao.getId());
        repositorio.delete(endereco);
    }
    
}
