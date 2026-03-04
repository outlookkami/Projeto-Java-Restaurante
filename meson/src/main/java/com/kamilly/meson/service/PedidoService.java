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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        pedido.setNumeroDia(ultimoNum == null ? 1 : ultimoNum + 1);
        pedido.setDataReferencia(hoje);
        pedido.setGarcom(usuarioLogado);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.ENVIADO);
        pedido.setValor(BigDecimal.ZERO);
        comandaRepository.save(comanda);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public ItemPedido adicionarItem(Long idPedido, Long idProduto, Integer quantidade, String observacao) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
        Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        BigDecimal precoUnitario = produto.getPreco();
        BigDecimal subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(quantidade);
        itemPedido.setPrecoUnitario(precoUnitario);
        itemPedido.setSubtotal(subtotal);
        itemPedido.setStatusItem(StatusItemPedido.ENVIADO);
        if (observacao != null && observacao.isBlank()) {
            observacao = null;
        }
        itemPedido.setObsItemPedido(observacao);
        itemPedidoRepository.save(itemPedido);

        if(pedido.getValor() == null) {
            pedido.setValor(BigDecimal.ZERO);
        }
        pedido.setValor(pedido.getValor().add(subtotal));
        pedidoRepository.save(pedido);

        Comanda comanda = pedido.getComanda();
        if(comanda.getValor() == null){
            comanda.setValor(BigDecimal.ZERO);
        }
        comanda.setValor(comanda.getValor().add(subtotal));
        comandaRepository.save(comanda);
        return itemPedido;
    }

    public List<Pedido> listarPedidosAbertosPorRestaurante(Restaurante restaurante) {
        return pedidoRepository.findAllByRestauranteAndStatus(restaurante, StatusPedido.ENVIADO);
    }

    public List<Pedido> buscarPedidosPorComanda(Long comandaId, Restaurante restaurante) {
        return pedidoRepository.buscarPedidos(comandaId, restaurante);
        //Comanda comanda = comandaRepository.findById(comandaId).orElseThrow(() -> new RuntimeException("Comanda não encontrada."));
        //return pedidoRepository.findAllByComandaAndRestaurante(comanda, restaurante);
    }

    public Pedido buscarPedidoPorId(Long id, Restaurante restaurante){
        return pedidoRepository.findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
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
