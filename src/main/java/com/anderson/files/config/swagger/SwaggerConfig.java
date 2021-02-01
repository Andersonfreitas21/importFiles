package com.anderson.files.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

  @Value("${spring.application.name}")
  private String serviceName;

  @Value("${spring.webflux.base-path}")
  private String basePath;

  @Bean
  OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(
            new Components()
                .addSecuritySchemes(
                    "bearer-jwt",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .in(In.HEADER)
                        .name("Authorization")
                        .bearerFormat("JWT")))
        .info(apiInfo())
        .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"));
  }

  private Info apiInfo() {
    return new Info()
        .title("NCC")
        .version("1.0.0")
        .license(new License().name("Apache 2.0").url("http://springdoc.org"));
  }

  @Bean
  GroupedOpenApi propostaAPI() {
    return GroupedOpenApi.builder()
        .group("Proposta")
        .pathsToMatch("/api/v**/proposta/**")
        .packagesToScan("br.com.f5promotora.ncc.api")
        .build();
  }

  @Bean
  GroupedOpenApi reapresentacaoAPI() {
    return GroupedOpenApi.builder()
        .group("Reapresentacao")
        .pathsToMatch("/api/v**/reapresentacao/**")
        .packagesToScan("br.com.f5promotora.ncc.api")
        .build();
  }

  @Bean
  GroupedOpenApi saldoAPI() {
    return GroupedOpenApi.builder()
        .group("Saldo")
        .pathsToMatch("/api/v**/saldo/**")
        .packagesToScan("br.com.f5promotora.ncc.api")
        .build();
  }

  @Bean
  GroupedOpenApi pendenciaAPI() {
    return GroupedOpenApi.builder()
        .group("Pendencia")
        .pathsToMatch("/api/v**/pendencia/**")
        .packagesToScan("br.com.f5promotora.ncc.api")
        .build();
  }

  @Bean
  GroupedOpenApi motivoAPI() {
    return GroupedOpenApi.builder()
        .group("Motivo")
        .pathsToMatch("/api/v**/motivo/**")
        .packagesToScan("br.com.f5promotora.ncc.api")
        .build();
  }

  @Bean
  GroupedOpenApi solicitacaoCancelamentoAPI() {
    return GroupedOpenApi.builder()
        .group("Cancelamento")
        .pathsToMatch("/api/v**/cancelamento/**")
        .packagesToScan("br.com.f5promotora.ncc.api")
        .build();
  }

  @Bean
  GroupedOpenApi cancelamentoAPI() {
    return GroupedOpenApi.builder()
        .group("Solicitacao cancelamento")
        .pathsToMatch("/api/v**/solicitacao-cancelamento/**")
        .packagesToScan("br.com.f5promotora.ncc.api")
        .build();
  }

  @Bean
  GroupedOpenApi informacaoAPI() {
    return GroupedOpenApi.builder()
        .group("Informacao")
        .pathsToMatch("/api/v**/informacao/**")
        .packagesToScan("br.com.f5promotora.ncc.api")
        .build();
  }
}
