package com.example.ServerCanasta.Controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerCanasta.Dtos.CanastillaDTO;
import com.example.ServerCanasta.Services.IServicesCanastillaProducida;
import com.example.ServerCanasta.config.ResourceNotFoundException;

@RestController
@RequestMapping("/canastilla")
@CrossOrigin(origins = "*")
public class CanastillaController {
	
	@Autowired
	IServicesCanastillaProducida canastillaServices;
	
	 /**
     * Obtiene todas las canastillas registradas.
     */
    @GetMapping("listar")
    public ResponseEntity<?> listar() {
        try {
            List<CanastillaDTO> lista = canastillaServices.listarCanastillas();

            if (lista.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(Map.of("mensaje", "No hay canastillas registradas."));
            }

            return ResponseEntity.ok(lista);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al listar las canastillas."));
        }
    }
	
    /**
     * Obtiene una canastilla por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            CanastillaDTO dto = canastillaServices.obtenerCanastillaPorID(id);
            if (dto == null)
                throw new ResourceNotFoundException("Canastilla no encontrada con ID: " + id);

            return ResponseEntity.ok(dto);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error interno al obtener la canastilla."));
        }
    }
    
    /*
     * Obtiene las canastillas totalmente revisadas
     */
    
    @GetMapping("/revisadas")
    public ResponseEntity<?> listaRevisadas(){
    	
    	try {
    		List<CanastillaDTO> lista = canastillaServices.listarCanastillasRevisadas();
    		
    		if(lista.isEmpty()) {
    			return ResponseEntity.status(HttpStatus.NO_CONTENT)
    					.body(Map.of("mensaje", "No hay canastillas revisadas"));
    		}
    		
    		 return ResponseEntity.ok(lista);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
	        return ResponseEntity.internalServerError()
	                .body(Map.of("error", "Error al listar las canastillas revisadas."));
		}
    }
	
}
