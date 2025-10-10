package com.example.ServerCanasta.Controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerCanasta.Dtos.ComposicionDTO;
import com.example.ServerCanasta.Services.IServicesComposicionProducida;
import com.example.ServerCanasta.config.BusinessException;
import com.example.ServerCanasta.config.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/composicion")
public class ComposicionController {
	
	@Autowired
	IServicesComposicionProducida composicionServices;

	 /**
     * Lista todas las composiciones registradas.
     */
    @GetMapping("listar")
    public ResponseEntity<?> listar() {
        try {
            List<ComposicionDTO> lista = composicionServices.listaComposiciones();
            
            if (lista.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(Map.of("mensaje", "No hay composiciones registradas."));
            
            return ResponseEntity.ok(lista);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error al listar las composiciones."));
        }
    }
	
    /**
     * Lista las composiciones asociadas a una canastilla.
     */
    @GetMapping("/canastilla/{idCanastilla}")
    public ResponseEntity<?> listarPorCanastilla(@PathVariable Integer idCanastilla) {
        try {
            List<ComposicionDTO> lista = composicionServices.listarComposicionesPorCanastilla(idCanastilla);

            if (lista == null || lista.isEmpty())
                throw new ResourceNotFoundException("No se encontraron composiciones para la canastilla " + idCanastilla);

            return ResponseEntity.ok(lista);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error interno al obtener composiciones."));
        }
    }

	
    /**
     * Marca un artículo como revisado.
     */
    @PutMapping("/marcarRevisado/{idCanastilla}/{articuloCodigo}")
    public ResponseEntity<?> marcarRevisado(
            @PathVariable Integer idCanastilla,
            @PathVariable String articuloCodigo) {

        try {
            ComposicionDTO composicionRevisada = composicionServices.marcarRevisado(idCanastilla, articuloCodigo);
            if (composicionRevisada == null)
                throw new ResourceNotFoundException("No se encontró la composición para la canastilla " + idCanastilla);

            return ResponseEntity.ok(composicionRevisada);

        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
            
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error interno al marcar como revisado."));
        }
    }
    
}
