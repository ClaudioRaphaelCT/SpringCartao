package com.financeiro.faturamento.modules.global.advice;

import com.financeiro.faturamento.modules.cartao.exceptions.CartaoNotFoundException;
import com.financeiro.faturamento.modules.global.components.IResponseBuilder;
import com.financeiro.faturamento.modules.global.dto.GlobalExceptionResponse;
import com.financeiro.faturamento.modules.global.service.MessageSourceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@ControllerAdvice
@AllArgsConstructor
public class GlobalControllerAdvice {
    private final MessageSourceService messageSourceService;
    private final IResponseBuilder responseBuilder;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalExceptionResponse> handlerMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // 1. Mapeia os erros dos campos traduzindo cada um
        Map<String, List<String>> validationErros = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(groupingBy(
                        FieldError::getField,
                        mapping(fieldError -> messageSourceService.getMessage(
                                fieldError.getDefaultMessage()), toList())
                ));

        // 2. Busca título e detalhe do .properties
        String title = messageSourceService.getMessage("exception.validation.title");
        String detail = messageSourceService.getMessage("exception.validation.detail");

        // 3. Constrói a resposta
        GlobalExceptionResponse response = responseBuilder.exceptionResponse(
                status,
                title,
                detail,
                request,
                validationErros
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CartaoNotFoundException.class)
    public ResponseEntity<GlobalExceptionResponse> handlerNotFound(
            CartaoNotFoundException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        String title = status.getReasonPhrase();
        GlobalExceptionResponse response = responseBuilder.exceptionResponse(
                status,
                title,
                detail,
                request,
                null
        );

        return ResponseEntity.status(status).body(response);
    }
}
