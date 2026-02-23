package com.financeiro.faturamento.modules.cartao.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum NomeResponsavel {
    RAPHAEL, RHAÍSSA, AMBOS;

    @JsonCreator
    public static NomeResponsavel fromValue(String value) {
        if (value == null) return null;
        return Arrays.stream(values())
                .filter(n -> n.name().equalsIgnoreCase(value.trim()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
