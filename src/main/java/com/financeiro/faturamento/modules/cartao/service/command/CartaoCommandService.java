package com.financeiro.faturamento.modules.cartao.service.command;

import com.financeiro.faturamento.modules.cartao.dto.CartaoRequest;
import com.financeiro.faturamento.modules.cartao.dto.CartaoResponse;
import com.financeiro.faturamento.modules.global.dto.GlobalResponse;

public interface CartaoCommandService {
    GlobalResponse<CartaoResponse> inserir(CartaoRequest request);

    GlobalResponse<CartaoResponse> atualizar(Long id, CartaoRequest request);

    GlobalResponse<CartaoResponse> removerPorId(Long id);
}
