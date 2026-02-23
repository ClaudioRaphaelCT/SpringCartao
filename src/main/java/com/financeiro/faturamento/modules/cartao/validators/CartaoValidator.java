package com.financeiro.faturamento.modules.cartao.validators;

import com.financeiro.faturamento.modules.cartao.components.IResumoProjecao;
import com.financeiro.faturamento.modules.cartao.dto.NomeResponsavel;
import com.financeiro.faturamento.modules.cartao.dto.TotalResponsavelDTO;
import com.financeiro.faturamento.modules.cartao.exceptions.CartaoNotFoundException;
import com.financeiro.faturamento.modules.cartao.model.Cartao;
import com.financeiro.faturamento.modules.cartao.repository.CartaoRepository;
import com.financeiro.faturamento.modules.global.service.MessageSourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CartaoValidator {
    private final CartaoRepository repository;
    private final MessageSourceService message;

    public Cartao validIdNotExists(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new CartaoNotFoundException(message.getMessage("cartao.not.found", id)));
    }

    public List<Cartao> validNameExists(NomeResponsavel nome) {
        List<Cartao> lista = repository.findAllByNome(nome);

        if (lista.isEmpty()) {
            throw new CartaoNotFoundException(message.getMessage("cartao.not.found", nome));
        }
        return lista;
    }

    public TotalResponsavelDTO validarObterResumo(NomeResponsavel nomeResponsavel) {
        IResumoProjecao projecao;
        // Centraliza a decisão da Query
        if (nomeResponsavel == NomeResponsavel.AMBOS) {
            projecao = repository.obterSomaEContagem(nomeResponsavel);
        } else {
            projecao = repository.obterSomaEContagemProporcional(nomeResponsavel, NomeResponsavel.AMBOS);
        }

        // Tratamento de segurança para projeção ou valores nulos
        if (projecao == null) {
            return new TotalResponsavelDTO(nomeResponsavel, 0.0, 0L);
        }

        Double total = (projecao.getTotal() != null) ? projecao.getTotal() : 0.0;
        Long qtd = (projecao.getQuantidade() != null) ? projecao.getQuantidade() : 0L;

        return new TotalResponsavelDTO(nomeResponsavel, total, qtd);
    }
}
