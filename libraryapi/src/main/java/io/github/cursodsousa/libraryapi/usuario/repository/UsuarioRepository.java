package io.github.cursodsousa.libraryapi.usuario.repository;

import io.github.cursodsousa.libraryapi.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Usuario findByLogin(String login);
}