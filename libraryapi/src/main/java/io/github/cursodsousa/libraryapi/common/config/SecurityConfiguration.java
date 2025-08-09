package io.github.cursodsousa.libraryapi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/*
    ============================================
    CONFIGURAÇÃO BÁSICA DO SPRING SECURITY
    ============================================

    Objetivo:
    ----------
    Demonstrar uma configuração mínima e funcional do Spring Security,
    suficiente para proteger todas as requisições HTTP, utilizando
    autenticação via formulário e HTTP Basic.

    Conceitos importantes:
    ----------------------
    1. @Configuration:
       Indica que esta classe contém definições de beans do Spring.
       É lida no momento da inicialização do contexto.

    2. @EnableWebSecurity:
       Ativa os recursos de segurança da web fornecidos pelo Spring Security.
       Sem essa anotação, a configuração padrão do framework é usada.

    3. SecurityFilterChain:
       É o coração da configuração de segurança.
       Define quais filtros e regras serão aplicados às requisições.

    4. HttpSecurity:
       API fluente usada para definir:
       - Como a autenticação vai funcionar.
       - Quais endpoints serão protegidos ou liberados.
       - Configurações de sessão, CSRF, CORS etc.

    5. Beans:
       Tudo que for retornado por métodos anotados com @Bean é gerenciado
       pelo Spring e pode ser injetado em outras partes do sistema.

    ============================================
    Fluxo da configuração abaixo:
    ============================================

    Passo 1 - Desativar CSRF:
        Em APIs REST, geralmente desativamos CSRF pois não há sessão de
        formulário tradicional. Em aplicações web com formulário, deve-se avaliar
        antes de desativar.

    Passo 2 - Ativar login com formulário:
        Habilita uma página de login padrão gerada pelo Spring Security.
        O usuário pode sobrescrever com sua própria página.

    Passo 3 - Ativar HTTP Basic:
        Permite autenticação simples enviando usuário e senha no cabeçalho
        Authorization (útil para ferramentas como Postman ou integração entre sistemas).

    Passo 4 - Restringir acesso:
        .anyRequest().authenticated()
        Significa que qualquer requisição precisa estar autenticada.
        Pode-se substituir por regras mais específicas, como permitAll() para alguns endpoints.

    Passo 5 - Montar a cadeia de filtros:
        .build() finaliza e retorna o SecurityFilterChain configurado.
*/

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Configuração da segurança HTTP
        return http

                // 1. Desativa a proteção CSRF (não recomendado para apps com sessão tradicional)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Ativa autenticação via formulário (com a página de login padrão do Spring Security)
                .formLogin(Customizer.withDefaults())

                // 3. Ativa autenticação HTTP Basic (útil para testes e APIs)
                .httpBasic(Customizer.withDefaults())

                // 4. Define que TODAS as requisições precisam estar autenticadas
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )

                // 5. Constrói e retorna a cadeia de filtros
                .build();
    }
}

/*
    ============================================
    TESTANDO A CONFIGURAÇÃO
    ============================================

    1. Execute a aplicação.
    2. Acesse qualquer endpoint, por exemplo: http://localhost:8080/api/teste
    3. O Spring redirecionará para a tela de login ou pedirá autenticação Basic.
    4. O usuário e senha padrão são gerados no console:
       Usuário: user
       Senha: gerada no log do Spring Boot.
*/
