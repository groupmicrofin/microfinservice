package com.gmf.services.exception;

public class MicroBankAppException extends RuntimeException {

    private String errorMessage;

    public MicroBankAppException(String errorMessage){
        super(errorMessage);
        this.errorMessage=errorMessage;
    }
}
