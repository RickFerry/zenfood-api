package com.ferry.zenfoodapi.core.mapper;

import com.ferry.zenfoodapi.domain.model.Cozinha;
import com.ferry.zenfoodapi.domain.model.dto.request.RefCozinhaId;
import com.ferry.zenfoodapi.domain.model.dto.response.CozinhaResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CozinhaMapper {
    private final ModelMapper mapper;

    public Cozinha toModel(RefCozinhaId dto) {
        return mapper.map(dto, Cozinha.class);
    }

    public CozinhaResponse toDto(Cozinha cozinha) {
        return mapper.map(cozinha, CozinhaResponse.class);
    }

    public List<CozinhaResponse> toCollectionDto(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
