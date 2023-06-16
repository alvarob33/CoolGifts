package com.example.coolgifts.api;

public class ApiException extends Exception {

    /**
     * Constructor de la clase BusinessException.
     * @param message El mensaje que describe la excepción.
     * @param cause La causa subyacente de la excepción.
     */
    public ApiException(String message, Exception cause) {
        super(message, cause);
    }
}
