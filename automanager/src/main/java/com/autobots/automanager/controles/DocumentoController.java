package com.autobots.automanager.controles;

import java.util.List;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.modelo.DocumentoAltera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.autobots.automanager.modelo.DocumentoCadastrar;
import com.autobots.automanager.modelo.DocumentoExclui;

@RestController
@RequestMapping("/documento")
public class DocumentoController {
    @Autowired
    private ClienteRepositorio repositorio;
    @Autowired
    private ClienteSelecionador selecionador;
    @Autowired
    private DocumentoCadastrar cadastraNovo;
    @Autowired
    private DocumentoAltera alteraDocumento;
    @Autowired
    private DocumentoExclui deletador;

    @PostMapping("/cadastro/{id}")
        public void CadastroDocumento(@RequestBody List<Documento> documento, @PathVariable long id) {
            List<Cliente> clientes = repositorio.findAll();
            Cliente cliente = selecionador.selecionar(clientes, id);
            cadastraNovo.cadastraNovo(cliente, documento);
            repositorio.save(cliente);
        }

    @GetMapping("/documento/{id}")
        public List<Documento> listarDocumentos(@RequestBody List<Documento> documento, @PathVariable long id) {
            List<Cliente> clientes = repositorio.findAll();
            return selecionador.selecionar(clientes, id).getDocumentos();
        }
    
    @PutMapping("/alterar/{id}")
        public void alterarDocumento(@RequestBody List<Documento> documento, @PathVariable long id) {
            List<Cliente> clientes = repositorio.findAll();
            Cliente cliente = selecionador.selecionar(clientes, id);
            alteraDocumento.atualiza(cliente.getDocumentos(), documento);
            repositorio.save(cliente);
        }    
    
    @DeleteMapping("/deletar/{id}")
        public void deletarDocumento(@RequestBody List<Documento> documento, @PathVariable long id){
            List<Cliente> clientes = repositorio.findAll();
            Cliente cliente = selecionador.selecionar(clientes, id);
            deletador.deletar(cliente, documento);
            repositorio.save(cliente);
        }
}
