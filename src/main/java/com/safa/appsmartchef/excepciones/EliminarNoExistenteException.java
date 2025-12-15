package com.safa.appsmartchef.excepciones;


public class EliminarNoExistenteException extends RuntimeException {
    public EliminarNoExistenteException(String mensaje){
        super(mensaje);
    }

}