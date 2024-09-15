package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.exceptions;

public class ListaNotFoundException extends  RuntimeException{
    public ListaNotFoundException() {
        super();
    }

    public ListaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListaNotFoundException(String message) {
        super(message);
    }
}
