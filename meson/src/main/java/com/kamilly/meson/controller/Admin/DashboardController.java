package com.kamilly.meson.controller.Admin;

import com.kamilly.meson.dto.response.ProdutoMaisVendidoDTO;
import com.kamilly.meson.service.RelatorioPdfService;
import com.kamilly.meson.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final RelatorioService relatorioService;
    private final RelatorioPdfService relatorioPdfService;

    @GetMapping
    public String exibirDashboard(Model model){

        return "admin/dashboard";
    }

    @GetMapping("/relatorios/produtos-mais-vendidos")
    public ResponseEntity<byte[]> relatorioProdutosMaisVendidos(@RequestParam LocalDate inicio, @RequestParam LocalDate fim) throws Exception {
        List<ProdutoMaisVendidoDTO> dados = relatorioService.produtosMaisVendidos(inicio, fim);
        byte[] pdf = relatorioPdfService.gerarRelatorioProdutosMaisVendidos(dados);
        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=produtos_mais_vendidos.pdf").contentType(MediaType.APPLICATION_PDF).body(pdf);
    }
}
