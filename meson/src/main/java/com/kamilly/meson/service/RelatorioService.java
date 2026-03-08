package com.kamilly.meson.service;

import com.kamilly.meson.dto.response.ProdutoMaisVendidoDTO;
import com.kamilly.meson.repository.ItemPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final ItemPedidoRepository itemPedidoRepository;

    public List<ProdutoMaisVendidoDTO> produtosMaisVendidos(LocalDate inicio, LocalDate fim) {
        return itemPedidoRepository.produtosMaisVendidos(inicio.atStartOfDay(), fim.atTime(23,59,59));
    }
}
