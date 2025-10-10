package com.example.ServerCanasta.Controladores;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ServerCanasta.Services.IServicesImportacion;
import com.example.ServerCanasta.config.ImportException;

@RestController
@RequestMapping("/importacion")
@CrossOrigin(origins="*")
public class ImportacionController {
	
	@Autowired
	IServicesImportacion importacionServices;
	
	 /**
     * Importa un archivo XML con canastillas y composiciones.
     */
    @PostMapping("/subir")
    public ResponseEntity<?> importarXml(@RequestParam MultipartFile file) {
        try {
            File tempFile = File.createTempFile("importacion-", ".xml");
            file.transferTo(tempFile);

            importacionServices.importarDesdeArchivo(tempFile);
            return ResponseEntity.ok(Map.of("mensaje", "Archivo importado correctamente."));

        } catch (ImportException e) {
        	
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", "Error al importar el archivo XML."));
        }
    }
}
