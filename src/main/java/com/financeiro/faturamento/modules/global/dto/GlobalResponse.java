package com.financeiro.faturamento.modules.global.dto;

public record GlobalResponse<T>(
        String mensagem,
        T dados
) {
}
