package io.github.cursodsousa.libraryapi.usuario.security;


import io.github.cursodsousa.libraryapi.usuario.model.Usuario;
import io.github.cursodsousa.libraryapi.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UsuarioService usuarioService;

    // alterado pois pegaremos o usuario logado do objeto Authentication
    //    public Usuario obterUsuarioLogado(){
    //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    //        String login = userDetails.getUsername();
    //        return usuarioService.obterPorLogin(login);
    //    }

    public Usuario obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof CustomAuthentication customAuthentication) {
            return customAuthentication.getUsuario();
        }

        return null;
    }

}