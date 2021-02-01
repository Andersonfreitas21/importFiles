package com.anderson.files.domain.data.wrapper.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CustomConverterLocalDate implements Converter<String, LocalDate> {
  @Override
  public LocalDate convert(String value) {
    if (value != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
      return LocalDate.parse(value, formatter);
    } else {
      return null;
    }
  }
}
