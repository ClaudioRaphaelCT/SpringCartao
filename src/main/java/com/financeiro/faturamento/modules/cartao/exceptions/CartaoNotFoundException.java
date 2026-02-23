package com.financeiro.faturamento.modules.cartao.exceptions;

public class CartaoNotFoundException extends RuntimeException {
    public CartaoNotFoundException(String message) {
        super(message);
    }
}
