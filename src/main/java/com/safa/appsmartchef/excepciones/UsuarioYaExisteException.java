package com.safa.appsmartchef.excepciones;

public class UsuarioYaExisteException extends RuntimeException {

    public UsuarioYaExisteException(String mensaje) {
        super(mensaje);
    }
}