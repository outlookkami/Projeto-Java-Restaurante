package com.kamilly.meson.controller;

import com.kamilly.meson.config.UsuarioDetails;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.PerfilUsuario;
import com.kamilly.meson.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String login(@Valid Usuario usuario, BindingResult result, Model model) {
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

    public Usuario usuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        if(authentication.getPrincipal() instanceof UsuarioDetails usuarioDetails){
            return usuarioDetails.getUsuario();
        }
        return null;
    }

    @GetMapping("/trocar-senha")
    public String trocarSenha(Model model){
        return "trocar-senha";
    }

    @GetMapping("admin/paginaInicial")
    public String admPagInicial(Model model){
        model.addAttribute("usuario", usuarioLogado());
        return "admin/paginaInicial";
    }

    @GetMapping("garcom/paginaInicial")
    public String garPagInicial(Model model){
        model.addAttribute("usuario", usuarioLogado());
        return "garcom/paginaInicial";
    }
}
