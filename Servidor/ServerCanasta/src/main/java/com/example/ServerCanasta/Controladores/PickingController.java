package com.example.ServerCanasta.Controladores;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ServerCanasta.Dtos.PickingItemDTO;
import com.example.ServerCanasta.Services.IServicesPickingItem;
import com.example.ServerCanasta.Services.IServiciesEmail;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/picking")
@CrossOrigin(origins = "*")
public class PickingController {
	
	@Autowired
	IServicesPickingItem pickingServices;
	
	@Autowired
	IServiciesEmail emailServices;
	
	 /**
     * Exporta los datos de picking desde la base de datos a un archivo XML local.
     * Este método se ejecuta para actualizar o generar el archivo de exportación.
     */
	@GetMapping("/exportar")
	public ResponseEntity<?> exportarPicking() {
	    try {
	        List<PickingItemDTO> items = pickingServices.generarPickingExport();

	        if (items == null || items.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT)
	                    .body(Map.of("mensaje", "No hay datos de picking para exportar."));
	        }

	        // Crear nombre del archivo con fecha
	        String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	        String fileName = "picking_export_" + fechaActual + ".xml";

	        // Generar archivo temporal
	        File tempFile = File.createTempFile("picking_export_", ".xml");
	        pickingServices.exportarXml(items, tempFile);

	        // Devolverlo como descarga directa
	        byte[] fileContent = java.nio.file.Files.readAllBytes(tempFile.toPath());
	        tempFile.deleteOnExit();

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
	                .contentType(MediaType.APPLICATION_XML)
	                .body(fileContent);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.internalServerError()
	                .body(Map.of("error", "Error al generar o exportar el XML"));
	    }
	}
	
	
	
	/**
	 * Controlador que gestiona las operaciones de exportación y envío de archivos XML
	 * relacionados con los pickings producidos.
	 * 
	 * Este método permite que el usuario desde la app móvil seleccione un archivo XML
	 * (que previamente descargó con el endpoint /exportar) y lo envíe por correo electrónico.
	 */

	@PostMapping(value = "/enviar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> enviarPickingPorCorreo(
	        @RequestParam("email") String email,              // Dirección del destinatario
	        @RequestPart("file") MultipartFile file) {        // Archivo XML adjuntado desde la app móvil

	    try {
	        /**
	         *  Validar que el usuario haya adjuntado un archivo.
	         * Si no hay archivo o está vacío, se devuelve un error 400 (Bad Request).
	         */
	        if (file.isEmpty()) {
	            return ResponseEntity.badRequest()
	                    .body(Map.of("error", "No se ha adjuntado ningún archivo XML."));
	        }

	        /**
	         *  Crear un archivo temporal en el servidor.
	         * Este archivo se usará como adjunto en el correo.
	         * - Se guarda temporalmente con prefijo 'picking_to_send_' y extensión '.xml'.
	         */
	        File tempFile = File.createTempFile("picking_to_send_", ".xml");
	        file.transferTo(tempFile); // Se transfiere el contenido del MultipartFile al archivo temporal

	        /**
	         *  Preparar los datos del correo electrónico:
	         * - Asunto
	         * - Cuerpo en formato HTML
	         * (Se puede personalizar con los datos de la empresa o del usuario)
	         */
	        String asunto = "Picking XML enviado desde app móvil";
	        String cuerpoHtml = """
	                <p>Hola,</p>
	                <p>Adjunto el archivo XML seleccionado por el usuario desde la app móvil.</p>
	                <p>Un saludo,<br><b>Servidor Picking</b></p>
	                """;

	        /**
	         *  Llamar al servicio de correo para enviar el mensaje.
	         * Este servicio se encarga de crear y enviar el correo con el archivo adjunto.
	         */
	        emailServices.enviarEmailConAdjunto(email, asunto, cuerpoHtml, tempFile);

	        /**
	         *  Si todo fue bien, se devuelve una respuesta 200 OK con los datos del envío.
	         */
	        return ResponseEntity.ok(Map.of(
	                "mensaje", "Correo enviado correctamente a " + email,
	                "archivo", file.getOriginalFilename()
	        ));

	    } catch (MessagingException e) {
	        /**
	         *  Error relacionado con el envío de correo (problemas SMTP, autenticación, etc.)
	         */
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Map.of("error", "Error al enviar el correo: " + e.getMessage()));

	    } catch (Exception e) {
	        /**
	         * Error general al manejar el archivo o el proceso de envío.
	         * Se imprime traza para depuración y se devuelve 500 Internal Server Error.
	         */
	        e.printStackTrace();
	        return ResponseEntity.internalServerError()
	                .body(Map.of("error", "Error general al procesar o enviar el archivo XML."));
	    }
	}
}
	