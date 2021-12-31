package com.br.bank.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MessagesResponse {

    INVALID_ACCOUNT_NUMBER("Número de conta não encontrado!"),
    INVALID_ACCOUNT_NAME("Nome não encontrado para consulta!"),
    INVALID_DATA("Dados não encontrados para consulta!");

    private final String message;

    @JsonValue
    public String getMessage() { return message; }

}
