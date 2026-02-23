package com.financeiro.faturamento.modules.cartao.mappers;

import com.financeiro.faturamento.modules.cartao.dto.CartaoRequest;
import com.financeiro.faturamento.modules.cartao.dto.CartaoResponse;
import com.financeiro.faturamento.modules.cartao.model.Cartao;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartaoMappers {

    List<CartaoResponse> toDTOList(List<Cartao> entidades);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Cartao toEntity(CartaoRequest request);

    CartaoResponse toDTO(Cartao entity);

    void updateFromRequest(CartaoRequest request, @MappingTarget Cartao entity);
}
