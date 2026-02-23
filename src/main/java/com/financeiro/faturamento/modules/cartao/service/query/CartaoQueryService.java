package com.financeiro.faturamento.modules.cartao.service.query;

import com.financeiro.faturamento.modules.cartao.dto.CartaoResponse;
import com.financeiro.faturamento.modules.cartao.dto.NomeResponsavel;
import com.financeiro.faturamento.modules.cartao.dto.TotalResponsavelDTO;
import com.financeiro.faturamento.modules.global.dto.GlobalResponse;

import java.util.List;

public interface CartaoQueryService {
    GlobalResponse<List<CartaoResponse>> obterTodos();

    GlobalResponse<List<CartaoResponse>> obterCartaoPorNome(NomeResponsavel nome);

    TotalResponsavelDTO obterResumo(NomeResponsavel nome);


}
