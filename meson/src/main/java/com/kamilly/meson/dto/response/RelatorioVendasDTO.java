package com.kamilly.meson.dto.response;

import java.math.BigDecimal;

public record RelatorioVendasDTO(
        Long quantPedidos,
        BigDecimal faturamentoTotal,
        BigDecimal ticketMedio
) {}
