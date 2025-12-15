package com.safa.appsmartchef.excepciones;

public class ElementoNoEncontradoException extends RuntimeException{
    public ElementoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}