package com.kamilly.meson.service;

import com.kamilly.meson.dto.response.ComandaResDTO;
import com.kamilly.meson.dto.response.ItemPedidoResDTO;
import com.kamilly.meson.dto.response.PedidoResDTO;
import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Pedido;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusComanda;
import com.kamilly.meson.model.enums.StatusMesa;
import com.kamilly.meson.repository.ComandaRepository;
import com.kamilly.meson.repository.MesaRepository;
import com.kamilly.meson.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ComandaService {

    private final ComandaRepository comandaRepository;
    private final MesaRepository mesaRepository;
    private final UsuarioService usuarioService;

    @Transactional(readOnly = true)
    public List<ComandaResDTO> buscarComandasAbertasMesa(Long mesaId, Restaurante restaurante) {
        Mesa mesa = mesaRepository.findById(mesaId).orElseThrow();
        List<Comanda> comandas = comandaRepository.findAllByMesaAndRestauranteAndStatus(mesa, restaurante, StatusComanda.ABERTA);
        return comandas.stream().map(comanda -> {
            List<PedidoResDTO> pedidosDTO = comanda.getPedidos().stream().map(pedido -> {
                List<ItemPedidoResDTO> itensDTO = pedido.getItens().stream().map(itemPedido ->
                    new ItemPedidoResDTO(
                            itemPedido.getProduto().getNome(),
                            itemPedido.getQuantidade(),
                            itemPedido.getSubtotal()
                    )
                ).toList();

                return new PedidoResDTO(
                        pedido.getStatus(),
                        pedido.getValor(),
                        itensDTO
                );
            }).toList();

            BigDecimal valor = comanda.calcularValor();
            return new ComandaResDTO(
                    comanda.getId(),
                    comanda.getNomeCliente(),
                    comanda.getMesa().getNumero(),
                    comanda.getStatus(),
                    valor,
                    pedidosDTO
            );
         }).toList();
    }

    public List<Comanda> listarComandasAbertasRestaurante(Restaurante restaurante) {
        return comandaRepository.findAllByRestauranteAndStatus(restaurante, StatusComanda.ABERTA);
    }

    public Comanda abrirComanda(Long mesaId, String nomeCliente, Restaurante restaurante) {
        Mesa mesa = mesaRepository.findByIdAndRestaurante(mesaId, restaurante).orElseThrow();
        Comanda comanda = new Comanda();
        comanda.setMesa(mesa);
        comanda.setNomeCliente(nomeCliente);
        comanda.setRestaurante(restaurante);
        comanda.setStatus(StatusComanda.ABERTA);
        comanda.setDataAbertura(LocalDateTime.now());
        mesa.setStatus(StatusMesa.OCUPADA);
        mesaRepository.save(mesa);
        return comandaRepository.save(comanda);
    }

    private void atualizarValorComanda(Comanda comanda) {
        BigDecimal valor = comanda.getPedidos().stream().map(Pedido::getValor).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        comanda.setValor(valor);
    }

    @Transactional
    public void fecharComanda(Long comandaId) {
        Comanda comanda = comandaRepository.findById(comandaId).orElseThrow();
        comanda.setStatus(StatusComanda.FINALIZADA);
        Mesa mesa = comanda.getMesa();
        boolean existeOutraComandaAberta = comandaRepository.existsByMesaAndStatus(mesa.getId(),  StatusComanda.ABERTA);

        if(!existeOutraComandaAberta){
            mesa.setStatus(StatusMesa.DISPONIVEL);
        }
    }

    public List<Comanda> listarComandas(Restaurante restaurante) {
        return comandaRepository.findAllByRestaurante(restaurante);
    }

    public Comanda buscarComandaPorId(Long id, Restaurante restaurante){
        return comandaRepository.findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada."));
    }

//    public void salvarComanda(Comanda comanda, Restaurante restaurante){
//        comanda.setRestaurante(restaurante);
//        comandaRepository.save(comanda);
//    }
}
