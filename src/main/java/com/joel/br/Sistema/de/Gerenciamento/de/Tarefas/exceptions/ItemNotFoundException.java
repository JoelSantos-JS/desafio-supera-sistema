package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.exceptions;

public class ItemNotFoundException extends  RuntimeException{

    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
