package com.financeiro.faturamento.modules.global.dto;

import java.util.List;
import java.util.Map;

public record GlobalExceptionResponse(
        String type,
        String title,
        Integer status,
        String detail,
        String instance,
        Map<String, List<String>> errors
) {
}
