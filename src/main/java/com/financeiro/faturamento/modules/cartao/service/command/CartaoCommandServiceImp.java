package com.financeiro.faturamento.modules.cartao.service.command;

import com.financeiro.faturamento.modules.cartao.dto.CartaoRequest;
import com.financeiro.faturamento.modules.cartao.dto.CartaoResponse;
import com.financeiro.faturamento.modules.cartao.mappers.CartaoMappers;
import com.financeiro.faturamento.modules.cartao.model.Cartao;
import com.financeiro.faturamento.modules.cartao.repository.CartaoRepository;
import com.financeiro.faturamento.modules.cartao.validators.CartaoValidator;
import com.financeiro.faturamento.modules.global.dto.GlobalResponse;
import com.financeiro.faturamento.modules.global.service.MessageSourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CartaoCommandServiceImp implements CartaoCommandService {
    private final MessageSourceService message;
    private final CartaoRepository repository;
    private final CartaoMappers mappers;
    private final CartaoValidator validator;

    @Override
    @Transactional
    public GlobalResponse<CartaoResponse> inserir(CartaoRequest request) {
        var cartao = mappers.toEntity(request);
        cartao = repository.save(cartao);
        return new GlobalResponse<>(
                message.getMessage("cartao_inserir_sucesso", request.nome()),
                mappers.toDTO(cartao)
        );
    }

    @Override
    @Transactional
    public GlobalResponse<CartaoResponse> atualizar(Long id, CartaoRequest request) {
        var cartaoExistente = validator.validIdNotExists(id);
        mappers.updateFromRequest(request, cartaoExistente);
        var cartaoAtualizado = repository.save(cartaoExistente);
        return new GlobalResponse<>(
                message.getMessage("cartao_atualizar_sucesso", request.nome()),
                mappers.toDTO(cartaoAtualizado)
        );
    }

    @Override
    @Transactional
    public GlobalResponse<CartaoResponse> removerPorId(Long id) {
        Cartao cartao = validator.validIdNotExists(id);
        repository.delete(cartao);
        return new GlobalResponse<>(
                message.getMessage("cartao.remover.id"),
                null
        );
    }
}
