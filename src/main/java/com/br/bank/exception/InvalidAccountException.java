package com.br.bank.exception;

import com.br.bank.model.dto.FailResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class InvalidAccountException extends RuntimeException{
    private static final long serialVersionUID = -1564898005448211280L;

    private String logMessage;
    private FailResponseDTO failResponseDTO;
    private HttpStatus httpStatus;

    public InvalidAccountException(String logMessage, FailResponseDTO failResponseDTO, HttpStatus httpStatus, Throwable cause) {
        super(logMessage, cause);
        this.logMessage = logMessage;
        this.failResponseDTO = failResponseDTO;
        this.httpStatus = httpStatus;
    }

    public InvalidAccountException(String logMessage, FailResponseDTO failResponseDTO, HttpStatus httpStatus) {
        super(logMessage);
        this.logMessage = logMessage;
        this.failResponseDTO = failResponseDTO;
        this.httpStatus = httpStatus;
    }

}
