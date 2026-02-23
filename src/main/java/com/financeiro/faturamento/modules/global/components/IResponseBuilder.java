package com.financeiro.faturamento.modules.global.components;

import com.financeiro.faturamento.modules.global.dto.GlobalExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public interface IResponseBuilder {
    GlobalExceptionResponse exceptionResponse(
            HttpStatus status,
            String title,
            String detail,
            HttpServletRequest request,
            Map<String, List<String>> errors
    );
}
