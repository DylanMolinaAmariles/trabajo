package com.example.ServerCanasta.Services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerCanasta.Dtos.PackingExportWrapper;
import com.example.ServerCanasta.Dtos.PickingItemDTO;
import com.example.ServerCanasta.Repository.RepositoryCanastillaProducida;
import com.example.ServerCanasta.Repository.RepositoryComposicionProducida;
import com.example.ServerCanasta.config.DtoConverter;
import com.example.ServerCanasta.modelo.CanastillaProducida;
import com.example.ServerCanasta.modelo.ComposicionProducida;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

@Service
public class ServicesPickingItemImpl implements IServicesPickingItem {

	@Autowired
	private RepositoryComposicionProducida composicionProducidaDAO;
	
	@Autowired
	private RepositoryCanastillaProducida canastillaProducidaDAO;
	
	@Autowired
	private DtoConverter dtoConverter;
	
	/**
	 * Genera una lista de objetos PickingItemDTO a partir de las composiciones
	 * almacenadas en la base de datos.
	 * 
	 * Este método realiza la exportación de datos necesaria para el proceso de picking:
	 * combina la información de cada  ComposicionProducida con la de su
	 * CanastillaProducida asociada, y transforma ambos en un DTO preparado para
	 * exportar o generar XML.
	 *
	 * @return una lista de  PickingItemDTO con todos los artículos y sus canastillas.
	 */
	
	 public List<PickingItemDTO> generarPickingExport() {
		 
		 // Obtenemos directamente todas las composiciones con su canastilla ya cargada
		    // (usando una query con JOIN FETCH en el repositorio, para evitar problemas de lazy loading).
		 
	        List<ComposicionProducida> composiciones = composicionProducidaDAO.findAllWithCanastilla();
	        
	     // Convertimos cada ComposicionProducida en un PickingItemDTO
	        
	        return composiciones.stream().map(c -> {
	          PickingItemDTO dto = new PickingItemDTO();
	        		 dto.setIdCanastillaProducida(c.getCanastillaProducida().getId());
	        		 dto.serCodigoCanastilla(c.getCanastillaProducida().getCodigo());
	        		 dto.setDescripcionCanastilla(c.getCanastillaProducida().getDescripcion());
	        		 dto.setCodigoArticulo(c.getArticuloCodigo());
	        		 dto.setDescripcionArticulo(c.getArticuloDescripcion());
	        		 dto.setFechaSolicitud(
	        				 c.getCanastillaProducida().getFechaSolicitud() != null
	        				 ? c.getCanastillaProducida().getFechaSolicitud().toString()
	        						 :null
	        				 );
	        		 dto.setCantidad(c.getCantidad());
	        		 return dto;
	        		 
	        }).toList();
	    }
	 
	 /**
	     * Exporta la lista de picking a un archivo XML en disco
	     *  items lista de PickingItemDTO a exportar
	     *  file archivo de salida
	     */

	@Override
	public void exportarXml(List<PickingItemDTO> items, File file) throws Exception {
		// TODO Auto-generated method stub
		
		// Creamos un wrapper que agrupa todos los items de picking
	    PackingExportWrapper wrapper = new PackingExportWrapper();
	    wrapper.setItems(items);

	    // Inicializamos el contexto JAXB con las clases que vamos a serializar
	    JAXBContext context = JAXBContext.newInstance(PackingExportWrapper.class, PickingItemDTO.class);

	    // Creamos el "marshaller" que se encargará de convertir los objetos Java a XML
	    Marshaller marshaller = context.createMarshaller();

	    // Configuramos para que el XML resultante quede bonito y con saltos de línea
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    // Escribimos el contenido en el archivo de salida
		marshaller.marshal(wrapper, file);
	}
	        
}