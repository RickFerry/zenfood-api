package com.ferry.zenfoodapi.domain.repository;

import com.ferry.zenfoodapi.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}