package com.example.ServerCanasta.modelo;

import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement(name = "CANASTILLAS_PRODUCIDAS")
public class CanastillaProducida {
	
	@XmlElement(name = "ID")
	private Integer id;
	
	@XmlElement(name = "CAN_PROD_COD")
	private String codigo;
	
	@XmlElement(name = "CAN_PROD_DESCR")
	private String descripcion;
	
	@XmlElement(name = "CAN_PROD_NOMBRE_PADRES")
	private String nonmbrePadres;
	
	@XmlElement(name = "CAN_PROD_FECHA_SOLICITUD")
	private LocalDateTime fechaSolicitud;
	
	@XmlElement(name = "CAN_PROD_OBSERV")
	private String observaciones;
	
	@XmlElement(name = "CAN_PROD_PRECIO")
	private Double precio;
	
	

}
