package com.kamilly.meson.service;

import com.kamilly.meson.model.ItemPedido;
import com.kamilly.meson.model.Pedido;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusItemPedido;
import com.kamilly.meson.model.enums.StatusPedido;
import com.kamilly.meson.repository.ItemPedidoRepository;
import com.kamilly.meson.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;

    public List<ItemPedido> listarItensPedido(Long pedidoId) {
        List<StatusItemPedido> statusItem = List.of(StatusItemPedido.ENVIADO, StatusItemPedido.EM_PREPARO);
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Pedido pedido = pedidoRepository.findByIdAndRestaurante(pedidoId, restaurante).orElseThrow();
        return itemPedidoRepository.findAllByPedidoIdAndStatusItemIn(pedidoId, statusItem);
    }

//    public List<ItemPedido> listarItensComanda(Long comandaId) {
//
//
//        return ;
//    }

    public long contagemItensRestantes(Pedido pedido) {
        return pedido.getItens().stream().filter(i -> i.getStatusItem() != StatusItemPedido.PRONTO).count();
    }

    public void atualizarStatus(Long itemId, String acao) {
        ItemPedido item = itemPedidoRepository.findById(itemId).orElseThrow();
        if(acao.equals("INICIAR")){
            item.setStatusItem(StatusItemPedido.EM_PREPARO);
            item.setDataPreparo(LocalDateTime.now());
        }
        if(acao.equals("FINALIZAR")) {
            item.setStatusItem(StatusItemPedido.PRONTO);
            item.setDataPronto(LocalDateTime.now());
        }
        if(acao.equals("ENTREGAR")){
            item.setStatusItem(StatusItemPedido.ENTREGUE);
            item.setDataEntrega(LocalDateTime.now());
        }
        itemPedidoRepository.save(item);
        conferirStatusPedido(item.getPedido().getId());
    }

    public void conferirStatusPedido(Long pedidoId) {
        List<ItemPedido> itens = itemPedidoRepository.findByPedidoId(pedidoId);
        boolean itensEntregues = itens.stream().allMatch(i -> i.getStatusItem() == StatusItemPedido.ENTREGUE);
        if(itensEntregues){
            Pedido pedido = pedidoRepository.findById(pedidoId).get();
            pedido.setStatus(StatusPedido.ENTREGUE);
            pedido.setDataEntrega(LocalDateTime.now());
            pedidoRepository.save(pedido);
        }
    }
}
