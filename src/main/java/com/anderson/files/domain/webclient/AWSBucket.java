package com.anderson.files.domain.webclient;

import com.anderson.files.domain.webclient.response.S3ObjectSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.SequenceInputStream;

@Service
@Slf4j
public class AWSBucket {

  private final WebClient webClient;

  private final RestTemplate restTemplate;

  @Value("${aws-base-url}")
  String AWS_API_BASE_URL;

  public AWSBucket(@Value("${aws-base-url}") String AWS_API_BASE_URL) {
    webClient =
        WebClient.builder()
            .baseUrl(AWS_API_BASE_URL)
            .filter(logRequest())
            .filter(logResponse())
            .build();
    this.restTemplate = new RestTemplateBuilder().build();
  }

  public Flux<S3ObjectSummary> getListObjects(String bucketName) {
    return webClient
        .get()
        .uri(uriBuilder -> uriBuilder.queryParam("bucketName", bucketName).build())
        .exchange()
        .flatMapMany(clientResponse -> clientResponse.bodyToFlux(S3ObjectSummary.class));
  }

  public Mono<Resource> download(String fileName) {
    return webClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/download").queryParam("fileName", fileName).build())
        .exchange()
        .flatMap(
            clientResponse -> {
              if (clientResponse.statusCode() != HttpStatus.OK) {
                return clientResponse
                    .bodyToMono(String.class)
                    .flatMap(
                        s ->
                            Mono.error(
                                new ResponseStatusException(clientResponse.statusCode(), s)));
              }
              return clientResponse.bodyToMono(byte[].class).map(ByteArrayResource::new);
            });
  }

  public Mono<String> upload(FilePart file, String fileName) {
    MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
    multipartBodyBuilder.asyncPart("file", file.content(), DataBuffer.class);
    return webClient
        .post()
        .uri("/upload")
        .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
        .retrieve()
        .onStatus(
            HttpStatus::isError,
            response ->
                response
                    .bodyToMono(String.class)
                    .flatMap(
                        responseString ->
                            Mono.error(
                                new ResponseStatusException(
                                    response.statusCode(), responseString))))
        .bodyToMono(String.class);
  }

  public Mono<String> upload2(FilePart file, String fileName) {
    return file.content()
        .map(DataBuffer::asInputStream)
        .reduce(SequenceInputStream::new)
        .flatMap(
            inputStream -> {
              try {
                return Mono.just(inputStream.readAllBytes());
              } catch (IOException ex) {
                return Mono.error(ex);
              }
            })
        .flatMap(
            bytes -> {
              MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
              multipartBodyBuilder.part("file", bytes);
              return webClient
                  .post()
                  .uri("/upload")
                  .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
                  .retrieve()
                  .onStatus(
                      HttpStatus::isError,
                      response ->
                          response
                              .bodyToMono(String.class)
                              .flatMap(
                                  responseString ->
                                      Mono.error(
                                          new ResponseStatusException(
                                              response.statusCode(), responseString))))
                  .bodyToMono(String.class);
            });
  }

  private ExchangeFilterFunction logRequest() {
    return (clientRequest, next) -> {
      log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
      clientRequest.headers().add(HttpHeaders.CONTENT_TYPE, "multipart/form-data");
      log.debug(clientRequest.body().toString());
      clientRequest
          .headers()
          .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
      return next.exchange(clientRequest);
    };
  }

  private ExchangeFilterFunction logResponse() {
    return ExchangeFilterFunction.ofResponseProcessor(
        clientResponse -> {
          log.info("Response: {}", clientResponse.headers().asHttpHeaders().get("property-header"));
          return Mono.just(clientResponse);
        });
  }

  public Mono<String> postFile(FilePart file, String filename) {
    return file.content()
        .map(DataBuffer::asInputStream)
        .reduce(SequenceInputStream::new)
        .flatMap(
            inputStream -> {
              try {
                return Mono.just(inputStream.readAllBytes());
              } catch (IOException ex) {
                return Mono.error(ex);
              }
            })
        .flatMap(
            bytes -> {
              String url = AWS_API_BASE_URL + "/upload";
              HttpMethod requestMethod = HttpMethod.POST;

              HttpHeaders headers = new HttpHeaders();
              headers.setContentType(MediaType.MULTIPART_FORM_DATA);

              MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
              ContentDisposition contentDisposition =
                  ContentDisposition.builder("form-data")
                      .name("file")
                      .filename(file.filename())
                      .build();

              fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
              HttpEntity<byte[]> fileEntity = new HttpEntity<>(bytes, fileMap);

              MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
              body.add("file", fileEntity);

              HttpEntity<MultiValueMap<String, Object>> requestEntity =
                  new HttpEntity<>(body, headers);

              ResponseEntity<String> response =
                  restTemplate.exchange(url, requestMethod, requestEntity, String.class);

              return Mono.just(response.getBody());
            });
  }
}
