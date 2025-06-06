package com.ferry.zenfoodapi.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferry.zenfoodapi.core.mapper.RestauranteMapper;
import com.ferry.zenfoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.ferry.zenfoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.ferry.zenfoodapi.domain.exception.ValidacaoException;
import com.ferry.zenfoodapi.domain.model.Cidade;
import com.ferry.zenfoodapi.domain.model.Cozinha;
import com.ferry.zenfoodapi.domain.model.Restaurante;
import com.ferry.zenfoodapi.domain.model.dto.request.RestauranteRequest;
import com.ferry.zenfoodapi.domain.model.dto.response.RestauranteResponse;
import com.ferry.zenfoodapi.domain.repository.CozinhaRepository;
import com.ferry.zenfoodapi.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;

@Service
@RequiredArgsConstructor
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;
    private final SmartValidator validator;
    private final RestauranteMapper restauranteMapper;
    private final CidadeService cidadeService;

    @Transactional(readOnly = true)
    public Page<RestauranteResponse> listar(Pageable pageable) {
        Page<Restaurante> restaurantes = restauranteRepository.findAll(pageable);
        return restauranteMapper.toPageDto(restaurantes);
    }

    @Transactional(readOnly = true)
    public RestauranteResponse buscar(Long id) {
        Restaurante restaurante = getRestauranteOrElseThrow(id);
        return restauranteMapper.toDto(restaurante);
    }

    @Transactional
    public RestauranteResponse salvar(RestauranteRequest restaurante) {
        Restaurante restauranteModel = restauranteMapper.toModel(restaurante);

        Cozinha cozinha = getCozinhaOrElseThrow(restaurante.getCozinha().getId());
        Cidade cidade = cidadeService.getCidadeOrElseThrow(restauranteModel.getEndereco().getCidade().getId());

        restauranteModel.setCozinha(cozinha);
        restauranteModel.getEndereco().setCidade(cidade);

        return restauranteMapper.toDto(restauranteRepository.save(restauranteModel));
    }

    @Transactional
    public RestauranteResponse atualizar(Long id, RestauranteRequest dto) {
        Restaurante restauranteAtual = getRestauranteOrElseThrow(id);
        Cozinha cozinha = getCozinhaOrElseThrow(dto.getCozinha().getId());
        Cidade cidade = cidadeService.getCidadeOrElseThrow(dto.getEndereco().getCidade().getId());

        restauranteMapper.updateModel(dto, restauranteAtual);

        restauranteAtual.setCozinha(cozinha);
        restauranteAtual.getEndereco().setCidade(cidade);

        return restauranteMapper.toDto(restauranteRepository.save(restauranteAtual));
    }

    @Transactional
    public RestauranteResponse atualizarParcial(Long id, Map<String, Object> campos, HttpServletRequest request) {
        Restaurante restauranteAtual = getRestauranteOrElseThrow(id);
        merge(campos, restauranteAtual, request);
        validador(restauranteAtual);
        RestauranteRequest dtoRequest = restauranteMapper.toDtoRequest(restauranteAtual);
        return this.atualizar(id, dtoRequest);
    }

    @Transactional
    public void remover(Long id) {
        try {
            restauranteRepository.delete(getRestauranteOrElseThrow(id));
        } catch (RestauranteNaoEncontradoException e) {
            throw new RestauranteNaoEncontradoException(String.format("Restaurante de código %d não encontrado", id));
        }
    }

    @Transactional
    public void ativar(Long id) {
        Restaurante restaurante = getRestauranteOrElseThrow(id);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long id) {
        Restaurante restaurante = getRestauranteOrElseThrow(id);
        restaurante.inativar();
    }

    private void validador(Restaurante restauranteAtual) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restauranteAtual, "restaurante");
        validator.validate(restauranteAtual, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);
        }
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
        ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

            camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                assert field != null;
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            throw new HttpMessageNotReadableException(e.getMessage(), getRootCause(e), httpRequest);
        }
    }

    private Cozinha getCozinhaOrElseThrow(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(
                () -> new CozinhaNaoEncontradaException(String.format("Cozinha de código %d não encontrada", cozinhaId)));
    }

    private Restaurante getRestauranteOrElseThrow(Long id) {
        return restauranteRepository.findById(id).orElseThrow(
                () -> new RestauranteNaoEncontradoException(String.format("Restaurante de código %d não encontrado", id)));
    }
}
