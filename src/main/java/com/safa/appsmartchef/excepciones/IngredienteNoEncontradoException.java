package com.safa.appsmartchef.excepciones;

public class IngredienteNoEncontradoException extends RuntimeException {
    public IngredienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
