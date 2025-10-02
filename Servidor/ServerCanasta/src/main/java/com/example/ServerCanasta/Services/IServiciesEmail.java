package com.example.ServerCanasta.Services;

import java.io.File;

import jakarta.mail.MessagingException;

public interface IServiciesEmail {
	
	void enviarEmailConAdjunto(String destinatario, String asunto, String mensaje, File archivo ) throws MessagingException;
}
