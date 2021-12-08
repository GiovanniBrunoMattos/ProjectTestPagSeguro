package com.br.bank.exception;


public class InvalidAccountException extends RuntimeException{

    private static final long serialVersionUID = -1564898005448211280L;
    private static final String ERROR_MESSAGE = "Account informed not found";

    public InvalidAccountException() {
        super(ERROR_MESSAGE);
    }
}
