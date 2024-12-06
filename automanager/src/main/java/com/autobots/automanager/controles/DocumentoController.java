package com.autobots.automanager.controles;

import java.util.List;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.repositorios.DocumentoRepositorio;
import com.autobots.automanager.modelo.DocumentoSelecionador;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/documento")
public class DocumentoController {
    @Autowired
    private DocumentoRepositorio repositorio;
    @Autowired
    private DocumentoSelecionador selecionador;
    @GetMapping("/documento/{id}")
    public Documento obterDocumento(@PathVariable long id) {
        List<Documento> documentos = repositorio.findAll();
        return selecionador.selecionar(documentos, id);
    }

    @GetMapping("/documentos")
    public List<Documento> obterDocumentos() {
        List<Documento> documentos = repositorio.findAll();
        return documentos;
    }

    @PutMapping("/atualizar")
    public void atualizarDocumento(@RequestBody Documento atualizacao) {
        Documento documento = repositorio.getById(atualizacao.getId());
        DocumentoAtualizador atualizador = new DocumentoAtualizador();
        atualizador.atualizar(documento, atualizacao);
        repositorio.save(documento);
    }

    @DeleteMapping("/excluir")
    public void excluirDocumento(@RequestBody Documento exclusao) {
        Documento documento = repositorio.getById(exclusao.getId());
        repositorio.delete(documento);
    }
}
