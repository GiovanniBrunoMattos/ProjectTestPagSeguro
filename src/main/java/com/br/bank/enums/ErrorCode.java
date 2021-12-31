package com.br.bank.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    INVALID_ACCOUNT_NUMBER_CODE("404.001"),
    INVALID_ACCOUNT_NAME_CODE("404.002"),
    INVALID_DATA_CODE("404.003");

    private String code;

    ErrorCode(String code) { this.code = code; }

    @JsonValue
    public String getCode() { return code; }
}
