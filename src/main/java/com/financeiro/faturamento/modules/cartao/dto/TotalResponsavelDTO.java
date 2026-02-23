package com.financeiro.faturamento.modules.cartao.dto;

public record TotalResponsavelDTO(
        NomeResponsavel nome,
        Double valorTotal,
        Long quantidadeTransacoes
) {
}
