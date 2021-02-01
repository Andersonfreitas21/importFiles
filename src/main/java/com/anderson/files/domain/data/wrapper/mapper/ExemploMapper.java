package com.anderson.files.domain.data.wrapper.mapper;

import com.anderson.files.domain.data.entity.Exemplo;
import com.anderson.files.domain.data.wrapper.dto.ExemploDTO;
import com.anderson.files.domain.data.wrapper.form.create.ExemploForm;
import org.mapstruct.Builder;

@org.mapstruct.Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface ExemploMapper extends Mapper<ExemploDTO, ExemploForm, Exemplo> {
}
