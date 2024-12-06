package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.modelo.TelefoneSelecionador;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

import java.util.List;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
    
    @Autowired
    private TelefoneRepositorio repositorio;

    @Autowired
    private TelefoneSelecionador selecionador;

    @GetMapping("/telefone/{id}")
    public Telefone obterTelefone(@PathVariable long id) {
        List<Telefone> telefones = repositorio.findAll();
        return selecionador.selecionar(telefones, id);
    }

    @GetMapping("/telefones")
    public List<Telefone> obterTelefones() {
        List<Telefone> telefones = repositorio.findAll();
        return telefones;
    }

    @PutMapping("/atualizar")
    public void atualizarTelefone(@RequestBody Telefone atualizacao) {
        Telefone telefone = repositorio.getById(atualizacao.getId());
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(telefone, atualizacao);
        repositorio.save(telefone);
    }

    @DeleteMapping("/excluir")
    public void excluirTelefone(@RequestBody Telefone exclusao) {
        Telefone telefone = repositorio.getById(exclusao.getId());
        repositorio.delete(telefone);
    }
}
