package com.kamilly.meson.controller;

import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.PerfilUsuario;
import com.kamilly.meson.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/validaSenha")
    public ResponseEntity<Boolean> validaSenha(@RequestParam String email, @RequestParam String senha){
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);

        if(optUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valida = encoder.matches(senha, optUsuario.get().getSenha());

        HttpStatus status = (valida) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valida);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha){
        Usuario usuario = null;
        
        if(usuario.getPerfilUsuario() == PerfilUsuario.ADMIN){
            return "redirect:/admin";
        }
        
        if(usuario.getPerfilUsuario() == PerfilUsuario.GARCOM){
            return "redirect:/garcom";
        }
        
        if(usuario.getPerfilUsuario() == PerfilUsuario.COZINHA){
            return "redirect:/cozinha";
        }
        
        return "redirect:/adminGeral";
    }
}
