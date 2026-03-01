package com.financeiro.faturamento.services;

import com.financeiro.faturamento.modules.cartao.dto.CartaoRequest;
import com.financeiro.faturamento.modules.cartao.dto.CartaoResponse;
import com.financeiro.faturamento.modules.cartao.dto.NomeResponsavel;
import com.financeiro.faturamento.modules.cartao.mappers.CartaoMappers;
import com.financeiro.faturamento.modules.cartao.model.Cartao;
import com.financeiro.faturamento.modules.cartao.repository.CartaoRepository;
import com.financeiro.faturamento.modules.cartao.service.command.CartaoCommandServiceImp;
import com.financeiro.faturamento.modules.cartao.validators.CartaoValidator;
import com.financeiro.faturamento.modules.global.service.MessageSourceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartaoCommandServiceImpTest {

    @Captor
    ArgumentCaptor<Cartao> cartaoCaptor;

    @Mock
    private MessageSourceService message;
    @Mock
    private CartaoRepository cartaoRepository;
    @Mock
    private CartaoMappers mappers;
    @Mock
    private CartaoValidator cartaoValidator;

    @InjectMocks
    private CartaoCommandServiceImp cartaoCommandService; // CLASSE CONCRETA AQUI

    @Test
    @DisplayName("Deve inserir um registro e retornar SUCESSO.")
    void deveValidarOsDadosEnviados() {
        // Arrange (Preparação de dados)
        var request = new CartaoRequest(NomeResponsavel.RAPHAEL, "Mercado", 110.11, LocalDate.now());
        var cartaoPersistido = new Cartao();
        var responseEsperada = new CartaoResponse(
                1L,
                LocalDateTime.now(),
                NomeResponsavel.RAPHAEL.name(),
                "Mercado", 110.11,
                LocalDate.now()
        );
        String msgSucesso = "Foi adicionado um registro para RAPHAEL com sucesso";

        // Configurar comportamentos
        when(mappers.toEntity(request)).thenReturn(cartaoPersistido);
        when(cartaoRepository.save(cartaoPersistido)).thenReturn(cartaoPersistido);
        when(mappers.toDTO(cartaoPersistido)).thenReturn(responseEsperada);
        when(message.getMessage("cartao_inserir_sucesso", request.nome())).thenReturn(msgSucesso);

        // Execução
        var resultado = cartaoCommandService.inserir(request);

        // validações
        verify(cartaoRepository).save(cartaoCaptor.capture());
        assertThat(resultado).isNotNull();
        assertThat(resultado.mensagem()).isEqualTo(msgSucesso);
        assertThat(resultado.dados()).isEqualTo(responseEsperada);

        // Verifica o fluxo
        verify(message, times(1)).getMessage("cartao_inserir_sucesso", request.nome());
    }
}
