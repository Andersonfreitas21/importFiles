package com.anderson.files.api.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

@Getter
@ToString
@Schema(name = "DefaultError")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultErrorResponse {
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime timestamp;

  private String path;
  private Integer code;
  private String message;

  public static DefaultErrorResponse of(String path, Integer code, String message) {
    return new DefaultErrorResponse(LocalDateTime.now(), path, code, message);
  }

  public static byte[] toByte(DefaultErrorResponse error) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(error).getBytes(Charset.defaultCharset());
    } catch (JsonProcessingException e) {
      return String.format("{\"message\":\"{}\"}", e.getMessage())
          .getBytes(Charset.defaultCharset());
    }
  }
}
