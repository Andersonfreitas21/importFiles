package com.anderson.files.domain.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface Service<DTO, Form, Filter, Document> {
    Flux<DTO> filter(Filter filter);

    Mono<DTO> save(Set<Form> forms);

    Mono<DTO> create(Form form);

    Mono<DTO> update(String id, Form form);

    Mono<DTO> get(String id);

    Mono<Document> find(String id);

    Mono<Void> delete(String id);
}
