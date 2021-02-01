package com.anderson.files.domain.data.wrapper.mapper;

import org.mapstruct.Mapping;

import java.util.Set;

public interface Mapper<DTO, Form, Document> {
    @Mapping(target = "id", source = "document._id")
    DTO documentToDto(Document document);

    Set<DTO> documentToDto(Set<Document> documentSet);

    Document formToDocument(Form form);

    Set<Document> formToDocument(Set<Form> forms);
}
