package com.financeiro.faturamento.modules.cartao.service.query;

import com.financeiro.faturamento.modules.cartao.dto.CartaoResponse;
import com.financeiro.faturamento.modules.cartao.dto.NomeResponsavel;
import com.financeiro.faturamento.modules.cartao.dto.TotalResponsavelDTO;
import com.financeiro.faturamento.modules.cartao.mappers.CartaoMappers;
import com.financeiro.faturamento.modules.cartao.repository.CartaoRepository;
import com.financeiro.faturamento.modules.cartao.validators.CartaoValidator;
import com.financeiro.faturamento.modules.global.dto.GlobalResponse;
import com.financeiro.faturamento.modules.global.service.MessageSourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartaoQueryServiceImp implements CartaoQueryService {
    private final CartaoRepository repository;
    private final CartaoMappers mappers;
    private final MessageSourceService message;
    private final CartaoValidator validator;

    @Override
    public GlobalResponse<List<CartaoResponse>> obterTodos() {
        var todasTransacoes = repository.findAll();
        return new GlobalResponse<>(
                message.getMessage("cartao_visualizar_todos"),
                mappers.toDTOList(todasTransacoes)
        );
    }

    @Override
    public GlobalResponse<List<CartaoResponse>> obterCartaoPorNome(NomeResponsavel nome) {
        var entities = validator.validNameExists(nome);
        return new GlobalResponse<>(
                message.getMessage("cartao_visualizar_nome", nome.toString().toUpperCase()),
                mappers.toDTOList(entities)
        );
    }

    @Override
    public TotalResponsavelDTO obterResumo(NomeResponsavel nome) {
        return validator.validarObterResumo(nome);
    }
}
