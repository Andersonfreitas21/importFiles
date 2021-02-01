package com.anderson.files.api.controller.v1;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Controller<DTO, Form, Filter> {
    Flux<DTO> filter(Filter filter);

    Mono<DTO> create(Form form);

    Mono<DTO> get(String id);

    default Mono<DTO> update(String id, Form form) {
        throw new UnsupportedOperationException();
    }
}
