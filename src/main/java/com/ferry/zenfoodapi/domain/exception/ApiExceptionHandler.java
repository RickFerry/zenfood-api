package com.ferry.zenfoodapi.domain.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. " +
            "Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorType errorType = ErrorType.ERRO_DE_SISTEMA;
        Error body = createErrorBuilder(status, MSG_USUARIO_FINAL, errorType)
                .userMessage(MSG_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException e, WebRequest request) {
        Error body = createErrorBuilder(HttpStatus.NOT_FOUND, e.getMessage(), ErrorType.ENTIDADE_NAO_ENCONTRADA)
                .userMessage(e.getMessage())
                .build();

        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException e, WebRequest request) {
        Error body = createErrorBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.ENTIDADE_EM_USO)
                .userMessage(e.getMessage())
                .build();

        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ViolacaoDeConstraintException.class)
    public ResponseEntity<Object> handleViolacaoDeConstraintException(
            ViolacaoDeConstraintException e, WebRequest request) {
        Error body = createErrorBuilder(HttpStatus.BAD_REQUEST, e.getMessage(), ErrorType.ERRO_NEGOCIO)
                .userMessage(MSG_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorType errorType = ErrorType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
        Error body = createErrorBuilder(status, detail, errorType)
                .userMessage(MSG_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable cause = ExceptionUtils.getRootCause(ex);
        if (cause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) cause, headers, status, request);
        } else if (ex.getCause() instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) ex.getCause(), headers, status, request);
        } else if (cause instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) cause, headers, status, request);
        }
        ErrorType errorType = ErrorType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
        Error body = createErrorBuilder(status, detail, errorType)
                .userMessage(MSG_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Error.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(MSG_USUARIO_FINAL)
                    .build();
        } else if (body instanceof String) {
            body = Error.builder()
                    .title((String) body)
                    .status(status.value())
                    .userMessage(MSG_USUARIO_FINAL)
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException cause, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorType errorType = ErrorType.PARAMETRO_INVALIDO;
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. " +
                        "Corrija e informe um valor compatível com o tipo %s.",
                cause.getName(), cause.getValue(), Objects.requireNonNull(cause.getRequiredType()).getSimpleName());

        Error body = createErrorBuilder(status, detail, errorType)
                .userMessage(MSG_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(cause, body, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(
            PropertyBindingException cause, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = cause.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .reduce((a, b) -> a + "." + b)
                .orElse("");

        String detail = String.format("A propriedade '%s' não existe. Corrija ou " +
                "remova essa propriedade e tente novamente.", path);

        Error body = createErrorBuilder(status, detail, ErrorType.MENSAGEM_INCOMPREENSIVEL)
                .userMessage(MSG_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(cause, body, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(
            InvalidFormatException cause, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = cause.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .reduce((a, b) -> a + "." + b)
                .orElse("");

        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. " +
                        "Corrija e informe um valor compatível com o tipo %s.",
                path, cause.getValue(), cause.getTargetType().getSimpleName());

        Error body = createErrorBuilder(status, detail, ErrorType.PARAMETRO_INVALIDO)
                .userMessage(MSG_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(cause, body, headers, status, request);
    }

    private Error.ErrorBuilder createErrorBuilder(HttpStatus status, String detail, ErrorType type) {
        return Error.builder()
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail);
    }

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Error {
        private Integer status;
        private String type;
        private String title;
        private String detail;

        @CreationTimestamp
        private LocalDateTime timestamp;
        private String userMessage;
    }
}
