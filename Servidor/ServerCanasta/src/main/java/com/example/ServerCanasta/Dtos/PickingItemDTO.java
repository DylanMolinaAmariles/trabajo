package com.example.ServerCanasta.Dtos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement(name = "PICKING_ITEM")
public class PickingItemDTO {
	
	@XmlElement(name = "ID_CAN_PROD")
	private Integer idCanastillaProducida;
	
	@XmlElement(name = "CAN_COD")
	private String codigoCanastilla;
	
	@XmlElement(name = "CAN_DESC")
	private String descripcionCanastilla;
	
	@XmlElement(name = "ART_COD")
	private String codigoArticulo;
	
	@XmlElement(name = "ART_DESC")
	private String descripcionArticulo;
	
	@XmlElement(name = "CAN_PROD_FECHA_SOLICITUD")
	private String fechaSolicitud;
	
	@XmlElement(name = "CANT_PROD")
	private int cantidad;
	
}
