package com.ferry.zenfoodapi.api.service;

import com.ferry.zenfoodapi.domain.exception.UsuarioNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Usuario;
import com.ferry.zenfoodapi.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Page<Usuario> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Usuario buscar(Long id) {
        return getUsuarioOrElseThrow(id);
    }

    private Usuario getUsuarioOrElseThrow(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException(String.format("Usuário com id %d não encontrado", id)));
    }
}
