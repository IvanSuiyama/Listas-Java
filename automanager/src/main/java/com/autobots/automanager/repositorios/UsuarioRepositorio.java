package com.autobots.automanager.repositorios;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobots.automanager.entitades.Documento;
import com.autobots.automanager.entitades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
    Set<Documento> findDocumentosById(Long userId);
    
}