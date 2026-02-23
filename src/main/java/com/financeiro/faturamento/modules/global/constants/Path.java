package com.financeiro.faturamento.modules.global.constants;

public final class Path {
    private Path() {
    }

    public static final class Global {
        public static final String MENSAGEM_SUCESSO = "classpath:/messages/global/global_sucesso";
        public static final String MENSAGEM_ERRO = "classpath:/messages/global/global_exceptions";
    }

    public static final class Cartao {
        public static final String MENSAGEM_SUCESSO = "classpath:/messages/cartao/cartao_sucesso";
        public static final String MENSAGEM_ERRO = "classpath:/messages/cartao/cartao_exceptions";
    }
}
