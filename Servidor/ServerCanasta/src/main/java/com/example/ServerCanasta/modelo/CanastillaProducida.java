package com.example.ServerCanasta.modelo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@XmlRootElement(name = "CANASTILLAS_PRODUCIDAS")

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class CanastillaProducida {
	
/*
 * Modelo de datos del xml de CANASTILLAS_PRODUCIDAS 
 * Necesario poner todas las variables del xml para su lectura 
 */
	
	@EqualsAndHashCode.Include
	@Id
	@XmlElement(name = "ID")
	private Integer id;
	
	@XmlElement(name = "CAN_PROD_COD")
	private String codigo;
	
	@XmlElement(name = "CAN_PROD_DESCR")
	private String descripcion;
	
	@XmlElement(name = "CAN_PROD_NOMBRE_PADRES")
	private String nombrePadres;
	
	@XmlElement(name = "CAN_PROD_FECHA_SOLICITUD")
	private LocalDateTime fechaSolicitud;
	
	@XmlElement(name = "CAN_PROD_OBSERV")
	private String observaciones;
	
	@XmlElement(name = "CAN_PROD_PRECIO")
	private Double precio;
	
	 /**
     * Relación OneToMany con la composicio que contiene este artículo.
     * 
     * - En BD genera una foreign key llamada "composicion_id".
     * - Permite acceder a todos los datos de la composicion desde la canastilla.
     */
	@OneToMany
	@JoinColumn(name = "composicion_id")
	private List<ComposicionProducida> compocicionesProducidas;
	
}
