package com.anderson.files.api.controller.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/attendant")
@RestController("attendantControllerV1")
public class Exemplo implements Controller<ExemploDTO, ExemploForm, ExamploFilter> {
}
