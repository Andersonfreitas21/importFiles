package com.anderson.files.api.controller.v1;

import com.anderson.files.domain.data.wrapper.dto.ExemploDTO;
import com.anderson.files.domain.data.wrapper.filter.ExemploFilter;
import com.anderson.files.domain.data.wrapper.form.create.ExemploForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/exemplo")
@RestController("exemploControllerV1")
public class Exemplo implements Controller<ExemploDTO, ExemploForm, ExemploFilter> {

  @Override
  public Flux<ExemploDTO> filter(ExemploFilter exemploFilter) {
    return null;
  }

  @Override
  public Mono<ExemploDTO> create(ExemploForm exemploForm) {
    return null;
  }

  @Override
  public Mono<ExemploDTO> get(String id) {
    return null;
  }
}
