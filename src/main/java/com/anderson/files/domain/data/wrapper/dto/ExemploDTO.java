package com.anderson.files.domain.data.wrapper.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(builderClassName = "Builder", setterPrefix = "set")
public class ExemploDTO {
  private String name;
  private String sobreNome;

  //    @lombok.Builder.Default private Set<InformacaoAdicionalDTO> informacoes = new HashSet<>();
  //    @lombok.Builder.Default private Set<SolicitacaoCancelamentoDTO> solicitacoes = new
  // HashSet<>();
  //
  //    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  //    @JsonSerialize(using = LocalDateTimeSerializer.class)
  //    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  //    private LocalDateTime createdAt;
  //
  //    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  //    @JsonSerialize(using = LocalDateTimeSerializer.class)
  //    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  //    private LocalDateTime updatedAt;

}
