package com.escalab.mediappbackend.service.impl;

import com.escalab.mediappbackend.model.Usuario;
import com.escalab.mediappbackend.repository.UsuarioRepo;
import com.escalab.mediappbackend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepo usuarioRepo;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrar(Usuario obj) {
        return usuarioRepo.save(obj);
    }

    @Override
    public Usuario modificar(Usuario obj) {
        return null;
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario leerPorId(Integer id) {
        return null;
    }

    @Override
    public boolean eliminar(Integer id) {
        return false;
    }
}
