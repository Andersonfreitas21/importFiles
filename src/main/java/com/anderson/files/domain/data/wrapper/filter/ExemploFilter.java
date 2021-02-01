package com.anderson.files.domain.data.wrapper.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(builderClassName = "Builder", setterPrefix = "set")
public class ExemploFilter {

    private Set<UUID> id;
    private String name;
    private String sobreNome;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdAtAfter;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdAtBefore;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate updatedAtAfter;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate updatedAtBefore;

}
