package io.github.cursodsousa.libraryapi.usuario.mapper;

import io.github.cursodsousa.libraryapi.usuario.dto.UsuarioDTO;
import io.github.cursodsousa.libraryapi.usuario.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
