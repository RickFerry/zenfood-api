package com.ferry.zenfoodapi.api.service;

import com.ferry.zenfoodapi.domain.exception.FormaPagamentoNaoEncontradaException;
import com.ferry.zenfoodapi.domain.model.FormaPagamento;
import com.ferry.zenfoodapi.domain.repository.FormaPagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FormaPagamentoService {
    private final FormaPagamentoRepository formaPagamentoRepository;

    @Transactional(readOnly = true)
    public Page<FormaPagamento> listar(Pageable pageable) {
        return formaPagamentoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public FormaPagamento buscar(Long id) {
        return getFormaPagamento(id);
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public FormaPagamento atualizar(Long id, FormaPagamento formaPagamento) {
        FormaPagamento formaPagamentoSalva = getFormaPagamento(id);
        BeanUtils.copyProperties(formaPagamento, formaPagamentoSalva, "id");
        return formaPagamentoRepository.save(formaPagamentoSalva);
    }

    @Transactional
    public void remover(Long id) {
        FormaPagamento formaPagamento = getFormaPagamento(id);
        formaPagamentoRepository.delete(formaPagamento);
    }

    private FormaPagamento getFormaPagamento(Long id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(
                        String.format("Forma de pagamento com id %d não encontrada", id)));
    }
}
