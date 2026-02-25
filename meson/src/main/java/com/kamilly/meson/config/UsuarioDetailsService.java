package com.kamilly.meson.config;

import com.kamilly.meson.config.exceptions.RestauranteInativoException;
import com.kamilly.meson.config.exceptions.UsuarioInativoException;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.repository.RestauranteRepository;
import com.kamilly.meson.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService{
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

            Usuario usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            Restaurante restaurante = usuario.getRestaurante();

            if (!restaurante.getAtivo()) {
                throw new RestauranteInativoException();
            } else if (!usuario.getAtivo()) {
                throw new UsuarioInativoException();
            }
            return new UsuarioDetails(usuario);
        }

   if(!usuario.getRestaurante().isAtivo()) {
       throw new DisabledException("Restaurante desativado");
    }

}
