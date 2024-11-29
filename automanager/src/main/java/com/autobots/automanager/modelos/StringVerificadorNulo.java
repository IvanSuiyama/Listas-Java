package com.autobots.automanager.modelos;

public class StringVerificadorNulo {

    public boolean verificar(String dado) {

        return dado == null || dado.isBlank(); // Returns true if dado is null or blank
		
    }
}
