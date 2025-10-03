package com.example.ServerCanasta.modelo;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "IMPORTACION")
public class ImportacionWrapper {
	
	
	@XmlElement(name = "CANASTILLAs_PRODUCIDAS")
	private List<CanastillaProducida> canastillas;
	
	@XmlElement(name = "COMPOSICION_PRODUCIDA")
	private List<ComposicionProducida> composiciones;
}
