package com.br.bank.exception;

import com.br.bank.enums.MessagesResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorMessageDTO implements Serializable {

    private static final long serialVersionUID = 7307702729555703990L;

    private String message;
    private int code;
    private String status;
    private String objectName;
    private List<ErrorObject> errors;

    public ErrorMessageDTO(String message){
        this.message = message;
    }

    public ErrorMessageDTO(MessagesResponse message){
        this.message = message.getMessage();
    }
}
