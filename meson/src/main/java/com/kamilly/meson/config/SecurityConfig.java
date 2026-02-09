package com.kamilly.meson.config;

import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.PerfilUsuario;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//            http
//                    // desativa CSRF (necessário pra não dar erro em POST/PUT)
//                    .csrf(csrf -> csrf.disable())
//
//                    // libera TODAS as rotas
//                    .authorizeHttpRequests(auth -> auth
//                            .anyRequest().permitAll()
//                    )
//
//                    // desativa tela de login
//                    .formLogin(form -> form.disable())
//
//                    // desativa auth básica (popup do navegador)
//                    .httpBasic(basic -> basic.disable())
//
//                    // desativa logout automático
//                    .logout(logout -> logout.disable());
//
//            return http.build();
//        }

//----------------------
    private final UsuarioService usuarioService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/cadastro/**",
                                "/css/**",
                                "/js/**",
                                "/api/**",
                                "/funcionarios/**"
                        ).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(loginSuccessHandler())
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioService.getUsuarioLogado();
            Restaurante restaurante = usuario.getRestaurante();

            if (usuario.getTrocarSenha() == true) {
                response.sendRedirect("/trocar-senha");
            } else {
                if (usuario.getPerfilUsuario() == PerfilUsuario.ADMIN_GERAL) {
                    response.sendRedirect("/admin-geral/dashboard");
                } else if (usuario.getPerfilUsuario() == PerfilUsuario.ADMIN) {
                    response.sendRedirect("/admin/paginaInicial");
                } else if (usuario.getPerfilUsuario() == PerfilUsuario.GARCOM) {
                    response.sendRedirect("/garcom/paginaInicial");
                } else {
                    response.sendRedirect("/cozinha/paginaInicial");
                }
            }
        };
    }
}

