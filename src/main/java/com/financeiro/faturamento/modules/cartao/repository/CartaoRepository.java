package com.financeiro.faturamento.modules.cartao.repository;

import com.financeiro.faturamento.modules.cartao.components.IResumoProjecao;
import com.financeiro.faturamento.modules.cartao.dto.NomeResponsavel;
import com.financeiro.faturamento.modules.cartao.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    @Query("SELECT SUM(c.valor) as total, COUNT(c) as quantidade FROM Cartao c WHERE c.nome = :nome")
    IResumoProjecao obterSomaEContagem(@Param("nome") NomeResponsavel nome);

    @Query("""
                SELECT 
                    SUM(
                        CASE 
                            WHEN c.nome = :nome THEN c.valor 
                            WHEN c.nome = :ambos THEN (c.valor / 2.0) 
                            ELSE 0.0 
                        END
                    ) as total, 
                    COUNT(c) as quantidade 
                FROM Cartao c 
                WHERE c.nome = :nome OR c.nome = :ambos
            """)
    IResumoProjecao obterSomaEContagemProporcional(
            @Param("nome") NomeResponsavel nome,
            @Param("ambos") NomeResponsavel ambos
    );

    List<Cartao> findAllByNome(NomeResponsavel nome);
}