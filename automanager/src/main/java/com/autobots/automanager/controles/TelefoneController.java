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

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.modelo.TelefoneCadastra;
import com.autobots.automanager.modelo.TelefoneRemove;
import com.autobots.automanager.repositorios.ClienteRepositorio;

import java.util.List;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {
    @Autowired
    private ClienteRepositorio Clienterepositorio;
    @Autowired
    private ClienteSelecionador Clienteselecionador;
    @Autowired
    private TelefoneCadastra cadastra;
    @Autowired
    private TelefoneAtualizador atualizador;
    @Autowired
    private TelefoneRemove Deleta;

     @PostMapping("/cadastrar/{id}")
    public void cadastrarTelefone(@RequestBody List<Telefone> telefones, @PathVariable long id){
        List<Cliente> clientes = Clienterepositorio.findAll();
        Cliente cliente = Clienteselecionador.selecionar(clientes, id);
        cadastra.cadastraNovo(cliente, telefones);
        Clienterepositorio.save(cliente);
    }

    @GetMapping("/telefone/{id}")
    public List<Telefone> visualizarTelefone(@PathVariable long id){
        List<Cliente> clientes = Clienterepositorio.findAll();
        return Clienteselecionador.selecionar(clientes, id).getTelefones();
    }

    @PutMapping("/atualizar/{id}")
    public void  atualizarTelefone(@RequestBody List<Telefone> telefones, @PathVariable long id){
        List<Cliente> clientes = Clienterepositorio.findAll();
        Cliente cliente = Clienteselecionador.selecionar(clientes, id);
        atualizador.atualizar(cliente.getTelefones(), telefones);
        Clienterepositorio.save(cliente);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirDocumento(@RequestBody List<Telefone> telefones, @PathVariable long id){
        List<Cliente> clientes = Clienterepositorio.findAll();
        Cliente cliente = Clienteselecionador.selecionar(clientes, id);
        Deleta.deletar(cliente, telefones);
        Clienterepositorio.save(cliente);
    }
}
