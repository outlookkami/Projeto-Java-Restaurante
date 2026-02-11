package com.kamilly.meson.service;

import com.kamilly.meson.model.*;
import com.kamilly.meson.repository.ComandaRepository;
import com.kamilly.meson.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ComandaRepository comandaRepository;

    public List<Pedido> listarPedidosPorComanda(Long comandaId, Restaurante restaurante) {
        Comanda comanda = comandaRepository.findById(comandaId).orElseThrow(() -> new RuntimeException("Comanda não encontrada."));
        return pedidoRepository.findAllByComandaAndRestaurante(comanda, restaurante);
    }


//    BigDecimal totalPedido = itemPedido.stream();
//    public BigDecimal totalPedido(List<ItemPedido> itensPedidos) {
//        BigDecimal totalPedido = itensPedidos.stream();
//        return itensPedidos.stream()
//                .mapToDouble(ip -> ip.getPrecoUnitario() * ip.getQuantidade())
//                .sum();
//    }
//
//    public BigDecimal adicionar10PorCento(){
//        return null;
//    }
}
