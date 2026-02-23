package com.financeiro.faturamento.modules.cartao.model;

import com.financeiro.faturamento.modules.cartao.dto.NomeResponsavel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cartao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private NomeResponsavel nome;
    private String local;
    private Double valor;
    private LocalDate data;

    @PrePersist
    void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
