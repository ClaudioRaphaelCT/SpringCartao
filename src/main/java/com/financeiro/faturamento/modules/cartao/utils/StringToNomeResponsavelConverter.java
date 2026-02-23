package com.financeiro.faturamento.modules.cartao.utils;

import com.financeiro.faturamento.modules.cartao.dto.NomeResponsavel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToNomeResponsavelConverter implements Converter<String, NomeResponsavel> {
    @Override
    public NomeResponsavel convert(String source) {
        return NomeResponsavel.fromValue(source);
    }
}
