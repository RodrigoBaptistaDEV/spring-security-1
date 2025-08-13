Referentes a cartões trello:

https://trello.com/c/WShnpZ88/239-implementa%C3%A7%C3%A3o-spring-security-1
https://trello.com/c/crVyJf3T/243-implementa%C3%A7%C3%A3o-spring-security-2


Repositório feito para mostrar :

# Implementação Spring Security

## Spring Security 1

Aqui vamos configurar o **Spring Security**, caminhando para a criação de um `usuario`, utilização de `UserDetails` e `UserService`, com **roles** e **auditoria do usuário logado**.

1. Adicionar a **dependência do Spring Security**.  

2. Adicionar uma **configuração básica** de `SecurityFilterChain` para poder testar a API protegida.  

3. **Feature – Autenticação usando o UserDetails**. *(link diff 3)*  
   a. Alteração no `security chain` permitindo **requests** de cadastro e adicionando criptografia.  
   b. Adição de componentes para o domínio de `usuario`:  
      - `usuarioController`  
      - DTO  
      - Entidade (`usuario`)  
      - Mapper  
      - `usuarioService`  
      - `usuarioRepository`  
   c. Adição de `UserDetailsService` customizado.

4. **Feature – Verificação de Roles**  
   - Uso de `@EnableMethodSecurity` e `@PreAuthorize`.

5. **Feature – Auditoria do Usuário Logado**  
   a. Adicionar `SecurityService` para pegar o usuário logado.  
   b. Adicionar nas *services* de cada domínio a utilização do método.

---

## Spring Security 2

Nesta etapa vamos substituir a lógica de geração de `UserDetails` e `UserService` puro para trabalhar com o objeto `Authentication` de forma customizada.

1. Criando nosso próprio objeto `Authentication` básico.  
   a. Substituir a lógica de `UserDetails` puro por um objeto `Authentication`, conforme recomendado pelo Spring Security.  
   b. Criar `CustomAuthentication` implementando `Authentication` (`springframework.security.core`).  
   c. Preencher os métodos `override` com a entidade de `Usuario` que estamos usando.

2. Criar um `AuthenticationProvider` customizado.

3. **Feature – Remover o prefixo de role padrão do Spring Security** para o `CustomAuthentication` funcionar.

4. Ajustar o `SecurityService` para capturar o `usuarioLogado` do nosso objeto `Authentication`.
