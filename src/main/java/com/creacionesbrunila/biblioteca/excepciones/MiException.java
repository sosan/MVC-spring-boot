package com.creacionesbrunila.biblioteca.excepciones;

public class MiException extends Exception {
//la creamos para diferenciar errores de la logica de negocio de los errores y bags del sistema

    public MiException(String message) {
        super(message);
    }

}
