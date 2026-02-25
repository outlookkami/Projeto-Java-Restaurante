package com.kamilly.meson.service;

import com.kamilly.meson.model.*;
import com.kamilly.meson.model.enums.StatusComanda;
import com.kamilly.meson.model.enums.StatusItemPedido;
import com.kamilly.meson.model.enums.StatusPedido;
import com.kamilly.meson.repository.ComandaRepository;
import com.kamilly.meson.repository.ItemPedidoRepository;
import com.kamilly.meson.repository.PedidoRepository;
import com.kamilly.meson.repository.ProdutoRepository;
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
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public List<Pedido> listarPedidosPorComanda(Long comandaId, Restaurante restaurante) {
        Comanda comanda = comandaRepository.findById(comandaId).orElseThrow(() -> new RuntimeException("Comanda não encontrada."));
        return pedidoRepository.findAllByComandaAndRestaurante(comanda, restaurante);
    }

    public Pedido abrirPedido(Long comandaId, Usuario usuarioLogado) {
        Comanda comanda = comandaRepository.findById(comandaId).orElseThrow(() -> new RuntimeException("Comanda não encontrada."));
        LocalDate hoje = LocalDate.now();
        Integer ultimoNum = pedidoRepository.buscarUltimoNumeroDia(hoje);
        Pedido pedido = new Pedido();
        pedido.setComanda(comanda);
        pedido.setRestaurante(comanda.getRestaurante());
        pedido.setNumeroDia(ultimo == null ? 1 : ultimo + 1);
        pedido.setDataReferencia(hoje);
        pedido.setGarcom(usuarioService.getUsuarioLogado());
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.ENVIADO);
        comandaRepository.save(comanda);
        return pedidoRepository.save(pedido);
    }

    public ItemPedido adicionarItem(Long idPedido, Long idProduto, Integer quantidade, String observacao) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
        Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(quantidade);
        itemPedido.setPrecoUnitario(produto.getPreco());
        if (observacao != null && observacao.isBlank()) {
            observacao = null;
        }
        itemPedido.setObsItemPedido(observacao);
        itemPedido.setStatusItem(StatusItemPedido.ENVIADO);
        itemPedido.setSubtotal(produto.getPreco().multiply(BigDecimal.valueOf(quantidade)));
        return itemPedidoRepository.save(itemPedido);
    }

    public List<Pedido> listarPedidosAbertosPorRestaurante(Restaurante restaurante) {
        return pedidoRepository.findAllByRestauranteAndStatus(restaurante, StatusPedido.ENVIADO);
    }

    public List<Pedido> buscarPedidosPorComanda(Long comandaId, Restaurante restaurante) {
        return pedidoRepository.buscarPedidos(comandaId, restaurante);
        //Comanda comanda = comandaRepository.findById(comandaId).orElseThrow(() -> new RuntimeException("Comanda não encontrada."));
        //return pedidoRepository.findAllByComandaAndRestaurante(comanda, restaurante);
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
