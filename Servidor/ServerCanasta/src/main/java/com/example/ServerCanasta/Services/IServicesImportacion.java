package com.example.ServerCanasta.Services;

import java.io.File;

import com.example.ServerCanasta.modelo.ImportacionWrapper;

import jakarta.xml.bind.JAXBException;

public interface IServicesImportacion {
	
	void importar(ImportacionWrapper wrapper);
	ImportacionWrapper leerXml(File xmlFile) throws JAXBException;
	void importarDesdeArchivo(File xmlFile) throws Exception ;

}
