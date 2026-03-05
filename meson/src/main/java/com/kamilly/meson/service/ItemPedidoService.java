package com.kamilly.meson.service;

import com.kamilly.meson.model.ItemPedido;
import com.kamilly.meson.model.Pedido;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusItemPedido;
import com.kamilly.meson.repository.ItemPedidoRepository;
import com.kamilly.meson.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return itemPedidoRepository.findAllByPedidoAndStatusItemIn(pedido, statusItem);
    }

    public long contagemItensRestantes(Pedido pedido) {
        return pedido.getItens().stream().filter(i -> i.getStatusItem() != StatusItemPedido.PRONTO).count();
    }

    public void atualizarStatus(Long itemId, String acao) {
        ItemPedido item = itemPedidoRepository.findById(itemId).orElseThrow();
        if(acao.equals("INICIAR")){
            item.setStatusItem(StatusItemPedido.EM_PREPARO);
        }
        if(acao.equals("FINALIZAR")) {
            item.setStatusItem(StatusItemPedido.PRONTO);
        }
        itemPedidoRepository.save(item);
    }
}
