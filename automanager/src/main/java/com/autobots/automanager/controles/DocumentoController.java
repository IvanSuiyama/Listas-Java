package com.autobots.automanager.controles;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.autobots.automanager.modelos.DocumentoCadastrar;
import com.autobots.automanager.modelos.DocumentoSelecionar;
import com.autobots.automanager.modelos.DocumentoExcluir;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelos.AdicionarLinkDocumento;
import com.autobots.automanager.modelos.DocumentoAtualizador;


@RestController
@RequestMapping("/documento")
public class DocumentoController {

    @Autowired
    private AdicionarLinkDocumento adicionarLink;

    @Autowired
    private DocumentoRepositorio repositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping("/documentos")
    public ResponseEntity<List<Documento>> obterDocumentos() {

        List<Documento> documentos = repositorio.findAll();

        if (documentos.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } else {

            adicionarLink.adicionarLink(documentos);

            return ResponseEntity.ok(documentos);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> obterDocumento(@PathVariable long id) {
        Documento documento = repositorio.findById(id).orElse(null);
        if (documento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            adicionarLink.adicionarLink(List.of(documento));
            return ResponseEntity.ok(documento);
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Documento>> obterClienteDocumentos(@PathVariable long id) {

        Cliente cliente = clienteRepositorio.findById(id).orElse(null);

        if (cliente == null || cliente.getDocumentos().isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } else {

            List<Documento> documentos = cliente.getDocumentos();

            adicionarLink.adicionarLink(documentos);

            return ResponseEntity.ok(documentos);

        }

    }

    @PostMapping("/cadastro/{clienteId}")
    public ResponseEntity<Void> cadastrarDocumento(@RequestBody List<Documento> documentos, @PathVariable long clienteId) {

        Cliente cliente = clienteRepositorio.findById(clienteId).orElse(null);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        DocumentoCadastrar cadastrador = new DocumentoCadastrar();

        cadastrador.cadastrar(cliente, documentos);

        clienteRepositorio.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/atualizar/{clienteId}")
    public ResponseEntity<Void> atualizarClienteDocumentos(@RequestBody List<Documento> atualizacao, @PathVariable long clienteId) {

        Cliente cliente = clienteRepositorio.findById(clienteId).orElse(null);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        DocumentoAtualizador atualizador = new DocumentoAtualizador();

        atualizador.atualizar(cliente.getDocumentos(), atualizacao);

        clienteRepositorio.save(cliente);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/excluir/{clienteId}")
    public ResponseEntity<Void> excluirClienteDocumentos(@RequestBody List<Documento> documentos, @PathVariable long clienteId) {

        Cliente cliente = clienteRepositorio.findById(clienteId).orElse(null);

        if (cliente == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        DocumentoExcluir excluidor = new DocumentoExcluir();

        excluidor.excluir(cliente, documentos);

        clienteRepositorio.save(cliente);

        return ResponseEntity.noContent().build();

    }
}
