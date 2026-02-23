package com.financeiro.faturamento.modules.global.components;

import com.financeiro.faturamento.modules.global.dto.GlobalExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ResponseBuilder implements IResponseBuilder {
    @Override
    public GlobalExceptionResponse exceptionResponse(
            HttpStatus status,
            String title,
            String detail,
            HttpServletRequest request,
            Map<String, List<String>> errors
    ) {
        return new GlobalExceptionResponse(
                "about:blank", // ou um link para sua documentação de erros
                title,
                status.value(),
                detail,
                request.getRequestURI(),
                errors
        );
    }
}
