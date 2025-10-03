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
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data

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
	private List<ComposicionProducida> CompocicionesProducidas;
	
	
	 // ================================
    // Constructores
    // ================================
    public CanastillaProducida() {}

    public CanastillaProducida(Integer id, String codigo, String descripcion, String nombrePadres,
                               LocalDateTime fechaSolicitud, String observaciones, Double precio) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.nombrePadres = nombrePadres;
        this.fechaSolicitud = fechaSolicitud;
        this.observaciones = observaciones;
        this.precio = precio;
    }

    // ================================
    // Getters y Setters
    // ================================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombrePadres() {
        return nombrePadres;
    }

    public void setNombrePadres(String nombrePadres) {
        this.nombrePadres = nombrePadres;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<ComposicionProducida> getComposiciones() {
        return CompocicionesProducidas;
    }

    public void setComposiciones(List<ComposicionProducida> composiciones) {
        this.CompocicionesProducidas = composiciones;
    }

    // ================================
    // Métodos utilitarios
    // ================================
    public void addComposicion(ComposicionProducida composicion) {
    	CompocicionesProducidas.add(composicion);
        composicion.setCanastillaProducida(this); // asegurar consistencia bidireccional
    }

    public void removeComposicion(ComposicionProducida composicion) {
    	CompocicionesProducidas.remove(composicion);
        composicion.setCanastillaProducida(null);
    }
	

}
