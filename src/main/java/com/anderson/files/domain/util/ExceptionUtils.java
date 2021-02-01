package com.anderson.files.domain.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

public abstract class ExceptionUtils {

  public static <T> Mono<T> notFound(String reason) {
    return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, reason));
  }

  public static <T> Mono<T> conflict(String reason) {
    return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, reason));
  }

  public static <T> Mono<T> badRequest(String reason) {
    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, reason));
  }

  public static <T> Mono<T> forbidden(String reason) {
    return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN, reason));
  }

  public static <T> Mono<T> unauthorized(String reason) {
    return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, reason));
  }

  public static <T> Mono<T> internalServerError(String reason) {
    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, reason));
  }
}
