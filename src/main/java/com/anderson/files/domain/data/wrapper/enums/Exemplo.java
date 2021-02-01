package com.anderson.files.domain.data.wrapper.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Exemplo {
    TIPO("tipo"),
    CATEGORIA("categoria");

    private final String descricao;
}
