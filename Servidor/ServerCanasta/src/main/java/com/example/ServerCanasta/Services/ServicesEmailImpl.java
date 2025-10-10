package com.example.ServerCanasta.Services;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 * Servicio encargado de enviar correos electrónicos utilizando JavaMailSender.
 */
@Service
public class ServicesEmailImpl implements IServiciesEmail {

    /**
     * Bean principal que proporciona las funcionalidades de envío de correo.
     * Spring Boot lo configura automáticamente a partir de las propiedades
     * definidas en el archivo de configuración.
     */
	
    @Autowired
    private JavaMailSender mailSender;
    
    
    // Si no se define mail.from.address, tomamos spring.mail.username por defecto
    @Value("${mail.from.address:${spring.mail.username}}")
    private String fromAddress;

    @Value("${mail.from.name:Servidor Picking}")
    private String fromName;

    /**
     * Envía un correo electrónico con un posible archivo adjunto.
     *
     * destinatario dirección del destinatario 
     * asunto       asunto o título del correo
     * mensaje      cuerpo del mensaje, puede ser texto plano o HTML
     * archivo      archivo adjunto 
     *	MessagingException si ocurre algún error durante la construcción o envío del correo
     */
    @Override
    public void enviarEmailConAdjunto(String destinatario, String asunto, String mensaje, File archivo)
            throws MessagingException, UnsupportedEncodingException {

        // Se crea una instancia de MimeMessage, que representa un correo electrónico
        // con estructura MIME (permite texto, HTML, imágenes y adjuntos).
        MimeMessage message = mailSender.createMimeMessage();

        // MimeMessageHelper simplifica la construcción del mensaje.
        // El parámetro 'true' indica que el correo será multiparte (para soportar adjuntos).
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        
        helper.setFrom(fromAddress, fromName);
        // Se establece el destinatario del correo.
        helper.setTo(destinatario);

        // Se establece el asunto o título del mensaje.
        helper.setSubject(asunto);

        // Se establece el cuerpo del mensaje.
        // El segundo parámetro 'true' indica que el texto es HTML (no texto plano).
        helper.setText(mensaje, true);

        // Si se proporciona un archivo adjunto y existe físicamente...
        if (archivo != null && archivo.exists()) {

            // Se crea un recurso del sistema de archivos a partir del archivo proporcionado.
            FileSystemResource file = new FileSystemResource(archivo);

            // Se agrega el archivo como adjunto al correo.
            // El primer parámetro define el nombre con el que aparecerá el adjunto.
            helper.addAttachment(archivo.getName(), file);
        }

        // Finalmente, se envía el mensaje usando el mailSender.
        mailSender.send(message);
    }
 
    
}


