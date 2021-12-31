package com.br.bank.model.dto;

import com.br.bank.enums.ErrorCode;
import com.br.bank.enums.MessagesResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FailResponseDTO {

    @JsonProperty("code")
    private ErrorCode code;

    @JsonProperty("description")
    private String description;

    public FailResponseDTO(ErrorCode code, MessagesResponse response) {
        this.code = code;
        this.description = response.getMessage();
    }

    public FailResponseDTO(ErrorCode code, String description) {
        this.code = code;
        this.description = description;
    }

}
