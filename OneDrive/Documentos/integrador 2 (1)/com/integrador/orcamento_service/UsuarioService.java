package com.integrador.orcamento_service;

import com.integrador.orcamento_entities.UsuarioEntity;
import com.integrador.orcamento_repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired 
    private UsuarioRepository usuarioRepository;

    // Retorna todos os usuários
    public List<UsuarioEntity> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Retorna um usuário por ID
    public UsuarioEntity getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Salva um novo usuário ou atualiza um existente
    public UsuarioEntity saveUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    // Deleta um usuário por ID
    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}
