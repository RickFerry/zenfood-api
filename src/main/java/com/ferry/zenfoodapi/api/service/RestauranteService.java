package com.ferry.zenfoodapi.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ferry.zenfoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.ferry.zenfoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Cozinha;
import com.ferry.zenfoodapi.domain.model.Restaurante;
import com.ferry.zenfoodapi.domain.repository.CozinhaRepository;
import com.ferry.zenfoodapi.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;

    @Transactional(readOnly = true)
    public Page<Restaurante> listar(Pageable pageable) {
        return restauranteRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Restaurante buscar(Long id) {
        return getRestauranteOrElseThrow(id);
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = getCozinhaOrElseThrow(cozinhaId);
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long id, Restaurante restaurante) {
        Restaurante restauranteAtual = getRestauranteOrElseThrow(id);
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = getCozinhaOrElseThrow(cozinhaId);
        copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro");
        restauranteAtual.setCozinha(cozinha);
        return restauranteRepository.save(restauranteAtual);
    }

    @Transactional
    public Restaurante atualizarParcial(Long id, Map<String, Object> campos) {
        Restaurante restauranteAtual = getRestauranteOrElseThrow(id);
        merge(campos, restauranteAtual);
        return this.atualizar(id, restauranteAtual);
    }

    @Transactional
    public void remover(Long id) {
        try {
            restauranteRepository.delete(getRestauranteOrElseThrow(id));
        } catch (RestauranteNaoEncontradoException e) {
            throw new RestauranteNaoEncontradoException(String.format("Restaurante de código %d não encontrado", id));
        }
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ObjectNode objectNode = objectMapper.convertValue(camposOrigem, ObjectNode.class);

        objectNode.fields().forEachRemaining(entry -> {
            String nomePropriedade = entry.getKey();
            JsonNode valorPropriedade = entry.getValue();

            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            if (field != null) {
                field.setAccessible(true);
                Object novoValor = objectMapper.convertValue(valorPropriedade, field.getType());
                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            }
        });
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
