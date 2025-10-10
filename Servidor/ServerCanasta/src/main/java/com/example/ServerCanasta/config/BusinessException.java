package com.example.ServerCanasta.config;

/**
 * Se lanza cuando ocurre un error de lógica o negocio
 * (por ejemplo, intentar marcar dos veces un artículo como revisado).
 */
public class BusinessException extends RuntimeException {
	
    public BusinessException(String message) {
        super(message);
    }
}
