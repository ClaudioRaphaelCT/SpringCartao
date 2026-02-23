package com.financeiro.faturamento.modules.cartao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CartaoRequest(
        @NotNull(message = "cartao.nome.obrigatorio")
        NomeResponsavel nome,
        String local,
        @NotNull(message = "cartao.valor.not.null")
        @Min(value = 1, message = "cartao.valor.min")
        Double valor,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data
) {
}
