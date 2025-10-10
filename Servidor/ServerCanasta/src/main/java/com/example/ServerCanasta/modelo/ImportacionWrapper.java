package com.example.ServerCanasta.modelo;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement(name = "IMPORTACION")
public class ImportacionWrapper {
	
	
	@XmlElement(name = "CANASTILLAs_PRODUCIDAS")
	private List<CanastillaProducida> canastillas;
	
	@XmlElement(name = "COMPOSICION_PRODUCIDA")
	private List<ComposicionProducida> composiciones;
	
}
