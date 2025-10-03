package com.example.ServerCanasta.Services;


import java.io.File;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerCanasta.Repository.RepositoryCanastillaProducida;
import com.example.ServerCanasta.Repository.RepositoryComposicionProducida;
import com.example.ServerCanasta.modelo.CanastillaProducida;
import com.example.ServerCanasta.modelo.ComposicionProducida;
import com.example.ServerCanasta.modelo.ImportacionWrapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
@Service
public class ServicesImportacionImpl implements IServicesImportacion{

	@Autowired
	private RepositoryCanastillaProducida canastillaProducidaDAO;
	
	@Autowired
	private RepositoryComposicionProducida composicionProducidaDAO;
	
	
	@Override
	public void importar(ImportacionWrapper wrapper) {
		// TODO Auto-generated method stub
		
		if(wrapper == null) return;
		
		// Obtenemos la fecha más nueva del XML (ejemplo: la fecha de la primera canastilla)
		
		LocalDateTime fechaNueva = wrapper.getCanastillas() !=null &&
				!wrapper.getCanastilla().isEmpty() ? wrapper.getCanastilla()
						.get(0).getFechaSolicitud(): null;
		 
		if(fechaNueva !=null) {
			
			// Borra solo lo que esta revisado y es anterior a la fecha actual
			composicionProducidaDAO.deleteByRevisadoTrueAndCanastilaProducida_FechaSolicitudBefore(fechaNueva.toLocalDate().atStartOfDay());
		}
		
		
		if(wrapper.getCanastillas !=null) {
			canastillaProducidaDAO.saveAll(wrapper.getCanastillas());
		}
		
		if(wrapper.getComposiciones !=null) {
			composicionProducidaDAO.saveAll(wrapper.getComposiciones());
		}
	
	}
	
	/**
     * Lee un archivo XML desde disco y lo transforma en objetos Java (wrapper).
     */

	@Override
	public ImportacionWrapper leerXml(File xmlFile) throws JAXBException{
		// TODO Auto-generated method stub
		JAXBContext context = JAXBContext.newInstance(
                ImportacionWrapper.class,
                CanastillaProducida.class,
                ComposicionProducida.class
        );
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ImportacionWrapper) unmarshaller.unmarshal(xmlFile);
	}

	/**
     * Método de conveniencia: lee un archivo XML y lo importa en la BD.
     */
	@Override
	public void importarDesdeArchivo(File xmlFile) throws Exception {
		// TODO Auto-generated method stub
		ImportacionWrapper wrapper = leerXml(xmlFile);
        importar(wrapper);
	}


}
