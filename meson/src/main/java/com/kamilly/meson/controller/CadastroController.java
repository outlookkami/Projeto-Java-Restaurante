package com.kamilly.meson.controller;

import com.kamilly.meson.controller.form.AdminForm;
import com.kamilly.meson.controller.form.RestauranteForm;
import com.kamilly.meson.dto.request.CadastroDTO;
import com.kamilly.meson.dto.request.RestauranteReqDTO;
import com.kamilly.meson.dto.request.UsuarioDTO;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.service.RestauranteService;
import com.kamilly.meson.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@RequestMapping("/cadastro")
@SessionAttributes("cadastro")
@RequiredArgsConstructor
public class CadastroController {

    private final RestauranteService restauranteService;
    private final UsuarioService usuarioService;
    private final PasswordEncoder encoder;

    @ModelAttribute("cadastro")
    public CadastroDTO cadastro() {
        return new CadastroDTO();
    }

    @GetMapping("/restaurante")
    public String cadRest(Model model){
        model.addAttribute("restauranteDTO", new RestauranteReqDTO());
        return "cadastro/restaurante";
    }

    @PostMapping("/restaurante")
    public String salvarRestaurante(@Valid @ModelAttribute("cadastro") CadastroDTO cadastroDTO, RestauranteReqDTO restauranteDTO, RestauranteForm restauranteForm){
        cadastroDTO.setCnpj(restauranteForm.getCnpj());
        cadastroDTO.setTelefone(restauranteForm.getTelefone());
        cadastroDTO.setAtivo(false);
        cadastroDTO.setStatus(StatusRestaurante.PENDENTE);

        return "redirect:/cadastro/admin";
    }

    @GetMapping("/admin")
    public String cadAdmin(@ModelAttribute("cadastro") CadastroDTO cadastroDTO, Model model){
        model.addAttribute("adminDTO", new UsuarioDTO());
        if (cadastroDTO.getCnpj() == null) {
            return "redirect:/cadastro/restaurante";
        }
        return "cadastro/admin";
    }

    @PostMapping("/admin")
    public String salvarAdmin(@Valid @ModelAttribute("cadastro") CadastroDTO cadastroDTO, AdminForm adminForm, SessionStatus status){

        cadastroDTO.setNomeAdmin(adminForm.getNome());
        cadastroDTO.setEmailAdmin(adminForm.getEmail());
        cadastroDTO.setSenhaAdmin(encoder.encode(adminForm.getSenha()));

        Restaurante restaurante = restauranteService.salvarRestaurante(cadastroDTO);

        Usuario admin = new Usuario();
        admin.setNome(cadastroDTO.getNomeAdmin());
        admin.setEmail(cadastroDTO.getEmailAdmin());
        admin.setSenha(cadastroDTO.getSenhaAdmin());

        usuarioService.criarAdmin(admin, restaurante);
        usuarioService.salvarUsuario(admin);

        status.setComplete();
        return "redirect:/cadastro/instrucoes";
    }

    @GetMapping("/instrucoes")
    public String instrucoes(){
        return "cadastro/instrucoes";
    }
}
