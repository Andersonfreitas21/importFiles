package com.anderson.files.domain.data.wrapper.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CustomConverterLocalDateTime implements Converter<String, LocalDateTime> {
  @Override
  public LocalDateTime convert(String value) {
    if (value != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
      return LocalDateTime.parse(value, formatter);
    } else {
      return null;
    }
  }
}
