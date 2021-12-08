package com.br.bank.exception;


public class CreateForbiddenException extends RuntimeException{

    private static final long serialVersionUID = -1564898005448211280L;
    private static final String ERROR_MESSAGE = "URL informed is invalid";

    public CreateForbiddenException() {
        super(ERROR_MESSAGE);
    }
}
