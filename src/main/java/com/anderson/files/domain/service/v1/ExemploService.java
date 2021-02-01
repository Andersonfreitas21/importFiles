package com.anderson.files.domain.service.v1;

import com.anderson.files.domain.data.entity.Exemplo;
import com.anderson.files.domain.data.wrapper.dto.ExemploDTO;
import com.anderson.files.domain.data.wrapper.filter.ExemploFilter;
import com.anderson.files.domain.data.wrapper.form.create.ExemploForm;
import com.anderson.files.domain.service.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public class ExemploService implements Service<ExemploDTO, ExemploForm, ExemploFilter, Exemplo> {
    @Override
    public Flux<ExemploDTO> filter(ExemploFilter exemploFilter) {
        return null;
    }

    @Override
    public Mono<ExemploDTO> save(Set<ExemploForm> exemploForms) {
        return null;
    }

    @Override
    public Mono<ExemploDTO> create(ExemploForm exemploForm) {
        return null;
    }

    @Override
    public Mono<ExemploDTO> update(String id, ExemploForm exemploForm) {
        return null;
    }

    @Override
    public Mono<ExemploDTO> get(String id) {
        return null;
    }

    @Override
    public Mono<Exemplo> find(String id) {
        return null;
    }

    @Override
    public Mono<Void> delete(String id) {
        return null;
    }
}
