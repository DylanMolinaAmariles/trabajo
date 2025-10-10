package com.example.ServerCanasta.config;

/**
 * Se lanza cuando un recurso solicitado no existe en la base de datos.
 */
public class ResourceNotFoundException extends RuntimeException {
	
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
