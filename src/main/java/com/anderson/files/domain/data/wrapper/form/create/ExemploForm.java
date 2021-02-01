package com.anderson.files.domain.data.wrapper.form.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(builderClassName = "Builder", setterPrefix = "set")
public class ExemploForm {

  private String name;
  private String sobreNome;

  //    @NotNull(message = "Forneça pelo menos um motivo")
  //    @NotEmpty(message = "Forneça pelo menos um motivo")
  //    private Set<UUID> motivos;
  //    @JsonIgnore
  //    private Set<UUID> solicitacoes;
}
