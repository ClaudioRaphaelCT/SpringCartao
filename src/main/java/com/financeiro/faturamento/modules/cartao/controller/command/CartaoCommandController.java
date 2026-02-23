package com.financeiro.faturamento.modules.cartao.controller.command;

import com.financeiro.faturamento.modules.cartao.dto.CartaoRequest;
import com.financeiro.faturamento.modules.cartao.dto.CartaoResponse;
import com.financeiro.faturamento.modules.cartao.service.command.CartaoCommandService;
import com.financeiro.faturamento.modules.global.dto.GlobalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cartao")
@AllArgsConstructor
public class CartaoCommandController {
    private final CartaoCommandService service;

    @PostMapping
    public ResponseEntity<GlobalResponse<CartaoResponse>> inserir(@Valid @RequestBody CartaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<CartaoResponse>> atualizar(
            @PathVariable Long id, @Valid @RequestBody CartaoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<CartaoResponse>> remover(@PathVariable Long id) {
        return ResponseEntity.ok(service.removerPorId(id));
    }
}
