package io.github.cursodsousa.libraryapi.usuario.security;

import io.github.cursodsousa.libraryapi.usuario.model.Usuario;
import io.github.cursodsousa.libraryapi.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var login = authentication.getName();
        var senhaDigitada = authentication.getCredentials().toString();

        Usuario usuarioEncontrado = usuarioService.obterPorLogin(login);

        if (Objects.isNull(usuarioEncontrado)) {
            throw getErrorUsuarioNaoEncontrado();
        }

        String senhaCriptografada = usuarioEncontrado.getSenha();

        boolean senhasMatch = encoder.matches(senhaDigitada, senhaCriptografada);

        if (senhasMatch) {
            return new CustomAuthentication(usuarioEncontrado);
        }

        throw getErrorUsuarioNaoEncontrado();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

    private UsernameNotFoundException getErrorUsuarioNaoEncontrado(){
        return new UsernameNotFoundException("Usuario e/ou senha incorretos");
    }
}
