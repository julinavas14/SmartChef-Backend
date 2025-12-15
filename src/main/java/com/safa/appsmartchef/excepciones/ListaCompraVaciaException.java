package com.safa.appsmartchef.excepciones;

public class ListaCompraVaciaException extends RuntimeException {
    public ListaCompraVaciaException(String mensaje) {
        super(mensaje);
    }
}