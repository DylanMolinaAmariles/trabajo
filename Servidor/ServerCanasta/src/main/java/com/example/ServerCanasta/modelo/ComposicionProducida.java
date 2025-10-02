package com.example.ServerCanasta.modelo;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement(name = "COMPOSICION_PRODUCIDA")
public class ComposicionProducida {
	
	@XmlElement(name = "COM_PROD_ID")
	private Integer id;
	
	@XmlElement(name = "COM_PROD_CAN_PROD")
	private String canastillaCodigo;

	@XmlElement(name = "COM_PROD_CAN_DESCR")
	private String canastillaDescripcion;
	
	@XmlElement(name = "COM_PROD_ART_PROD")
	private String articuloCodigo;
	
	@XmlElement(name = "COM_PROD_ART_DESCR")
	private String articuloDescripcion;
	
	@XmlElement(name = "CANTIDAD")
	private int cantidad = 0;
	
	@XmlTransient
	private Boolean revisado = false ;
	
	@XmlTransient
	private CanastillaProducida canastillaProducida;
}
