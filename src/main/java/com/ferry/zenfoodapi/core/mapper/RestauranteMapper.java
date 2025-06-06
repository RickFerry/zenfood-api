package com.ferry.zenfoodapi.core.mapper;

import com.ferry.zenfoodapi.domain.model.Cidade;
import com.ferry.zenfoodapi.domain.model.Cozinha;
import com.ferry.zenfoodapi.domain.model.Restaurante;
import com.ferry.zenfoodapi.domain.model.dto.request.RestauranteRequest;
import com.ferry.zenfoodapi.domain.model.dto.response.RestauranteResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestauranteMapper {
    private final ModelMapper mapper;

    public Restaurante toModel(RestauranteRequest dto) {
        return mapper.map(dto, Restaurante.class);
    }

    public RestauranteResponse toDto(Restaurante restaurante) {
        return mapper.map(restaurante, RestauranteResponse.class);
    }

    public RestauranteRequest toDtoRequest(Restaurante model) {
        return mapper.map(model, RestauranteRequest.class);
    }

    public List<RestauranteResponse> toCollectionDto(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Page<RestauranteResponse> toPageDto(Page<Restaurante> restaurantes) {
        return restaurantes.map(this::toDto);
    }

    public void updateModel(RestauranteRequest dto, Restaurante restaurante) {
        restaurante.setCozinha(new Cozinha());
        if (restaurante.getCozinha() != null) {
            restaurante.getEndereco().setCidade(new Cidade());
        }
        mapper.map(dto, restaurante);
    }
}
