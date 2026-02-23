package com.financeiro.faturamento.modules.cartao.controller.query;

import com.financeiro.faturamento.modules.cartao.dto.CartaoResponse;
import com.financeiro.faturamento.modules.cartao.dto.NomeResponsavel;
import com.financeiro.faturamento.modules.cartao.dto.TotalResponsavelDTO;
import com.financeiro.faturamento.modules.cartao.service.query.CartaoQueryService;
import com.financeiro.faturamento.modules.global.dto.GlobalResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cartao")
@AllArgsConstructor
public class CartaoQueryController {
    private final CartaoQueryService service;

    @GetMapping("/resumo/{nome}")
    public ResponseEntity<TotalResponsavelDTO> buscarResumo(@PathVariable NomeResponsavel nome) {
        return ResponseEntity.ok(service.obterResumo(nome));
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<CartaoResponse>>> visualizarTodos() {
        return ResponseEntity.ok(service.obterTodos());
    }

    @GetMapping("/responsavel/{nome}")
    public ResponseEntity<GlobalResponse<List<CartaoResponse>>> buscarPorNome(
            @PathVariable NomeResponsavel nome) {
        return ResponseEntity.ok(service.obterCartaoPorNome(nome));
    }
}
