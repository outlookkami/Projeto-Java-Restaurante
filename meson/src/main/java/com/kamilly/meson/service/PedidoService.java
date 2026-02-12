package com.kamilly.meson.service;

import com.kamilly.meson.model.*;
import com.kamilly.meson.model.enums.StatusComanda;
import com.kamilly.meson.model.enums.StatusPedido;
import com.kamilly.meson.repository.ComandaRepository;
import com.kamilly.meson.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ComandaRepository comandaRepository;
    private final UsuarioService usuarioService;

    public List<Pedido> listarPedidosPorComanda(Long comandaId, Restaurante restaurante) {
        Comanda comanda = comandaRepository.findById(comandaId).orElseThrow(() -> new RuntimeException("Comanda não encontrada."));
        return pedidoRepository.findAllByComandaAndRestaurante(comanda, restaurante);
    }

    public Pedido abrirPedido(Long comandaId, Usuario usuarioLogado) {
        Comanda comanda = comandaRepository.findById(comandaId).orElseThrow(() -> new RuntimeException("Comanda não encontrada."));
        Pedido pedido = new Pedido();
        pedido.setComanda(comanda);
        pedido.setRestaurante(comanda.getRestaurante());
        pedido.setGarcom(usuarioService.getUsuarioLogado());
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.RECEBIDO);
        comandaRepository.save(comanda);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> buscarPedidosPorComanda(Long comandaId, Restaurante restaurante) {
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
