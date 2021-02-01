package com.anderson.files.api.controller;

import feign.FeignException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class AdviceController {

  @ResponseBody
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public Mono<DefaultErrorResponse> dataIntegrity(Throwable ex, ServerHttpRequest request) {
    return Mono.just(
        DefaultErrorResponse.of(
            request.getPath().value(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
  }

  @ResponseBody
  @ExceptionHandler(ServerWebInputException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public Mono<DefaultErrorResponse> typeMismatch(
      ServerWebInputException ex, ServerHttpRequest request) {
    return Mono.just(
        DefaultErrorResponse.of(
            request.getPath().value(), HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage()));
  }

  @ResponseBody
  @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
  @ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public Mono<DefaultErrorResponse> unsupportedMediaType(Throwable ex, ServerHttpRequest request) {
    return Mono.just(
        DefaultErrorResponse.of(
            request.getPath().value(),
            HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
            ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()));
  }

  @ResponseBody
  @ExceptionHandler(ResponseStatusException.class)
  public Mono<ResponseEntity<DefaultErrorResponse>> genericError(
      Throwable ex, ServerHttpRequest request) {
    return Mono.just(
        ResponseEntity.status(((ResponseStatusException) ex).getStatus())
            .body(
                DefaultErrorResponse.of(
                    request.getPath().value(),
                    ((ResponseStatusException) ex).getStatus().value(),
                    ((ResponseStatusException) ex).getReason())));
  }

  @ResponseBody
  @ExceptionHandler(FeignException.class)
  public Mono<ResponseEntity<DefaultErrorResponse>> feignError(
      Throwable ex, ServerHttpRequest request) {
    int statusCode = 500;
    if (((FeignException) ex).status() != -1) {
      statusCode = ((FeignException) ex).status();
    }

    if (statusCode == 403) {
      statusCode = 401;
    }

    return Mono.just(
        ResponseEntity.status(statusCode)
            .body(
                DefaultErrorResponse.of(
                    request.getPath().value(),
                    ((FeignException) ex).status(),
                    ((FeignException) ex).contentUTF8())));
  }

  @ResponseBody
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public Mono<DefaultErrorResponse> netflixEx(Throwable ex, ServerHttpRequest request) {
    return Mono.just(
        DefaultErrorResponse.of(
            request.getPath().value(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()));
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
  @ExceptionHandler(UnsupportedOperationException.class)
  public Mono<DefaultErrorResponse> unsupportedOperationException(
      Throwable ex, ServerHttpRequest request) {
    return Mono.just(
        DefaultErrorResponse.of(
            request.getPath().value(),
            HttpStatus.NOT_IMPLEMENTED.value(),
            ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()));
  }
}
