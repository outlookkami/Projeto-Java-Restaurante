package com.kamilly.meson.controller.Admin;

import com.kamilly.meson.model.CategoriaProduto;
import com.kamilly.meson.model.Restaurante;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {
    @GetMapping
    public String exibirDashboard(Model model){
        return "admin/dashboard";
    }
}
