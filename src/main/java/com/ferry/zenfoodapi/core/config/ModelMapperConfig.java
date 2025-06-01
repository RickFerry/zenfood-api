package com.ferry.zenfoodapi.core.config;

import com.ferry.zenfoodapi.domain.model.Endereco;
import com.ferry.zenfoodapi.domain.model.dto.response.EnderecoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.lang.String.valueOf;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.createTypeMap(Endereco.class, EnderecoResponse.class).addMapping(
                src -> src.getCidade().getEstado().getNome(),
                (dest, value) -> dest.getCidade().setEstado(valueOf(value))
        );

        return modelMapper;
    }
}
