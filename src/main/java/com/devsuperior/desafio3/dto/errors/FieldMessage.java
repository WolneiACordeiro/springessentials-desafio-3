package com.devsuperior.desafio3.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldMessage {
    private String fieldName;
    private String message;
}
