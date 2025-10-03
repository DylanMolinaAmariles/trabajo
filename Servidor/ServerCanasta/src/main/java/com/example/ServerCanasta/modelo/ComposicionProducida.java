package com.example.ServerCanasta.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/*
 *  Lombok
 *  @Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
 * no funciona por tema de incompatibilidad en versiones 
 *  
 */		

@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder

@EqualsAndHashCode(onlyExplicitlyIncluded = true)

// JAXB para XML
@XmlRootElement(name = "COMPOSICION_PRODUCIDA")

// JPA para BD
@Entity
public class ComposicionProducida {

    /**
     * Identificador único de la composición (clave primaria en BD).
     * Coincide con el campo COM_PROD_ID del XML.
     */
	@EqualsAndHashCode.Include
    @Id
    @XmlElement(name = "COM_PROD_ID")
    private Integer id;

    @XmlElement(name = "COM_PROD_CAN_COD")
    private String canastillaCodigo;

    @XmlElement(name = "COM_PROD_CAN_DESCR")
    private String canastillaDescripcion;

    @XmlElement(name = "COM_PROD_ART_COD")
    private String articuloCodigo;

    @XmlElement(name = "COM_PROD_ART_DESCR")
    private String articuloDescripcion;
    
    @XmlElement(name = "CANTIDAD")
    private int cantidad = 0;

    @XmlTransient
    private Boolean revisado = false;

    /**
     * Relación ManyToOne con la canastilla que contiene este artículo.
     * 
     * - En BD genera una foreign key llamada "canastilla_id".
     * - Permite acceder a todos los datos de la canastilla desde la composición.
     */
    @ManyToOne
    @JoinColumn(name = "canastilla_id")
    private CanastillaProducida canastillaProducida;
    
    
    
    // ================================
    // Constructores
    // ================================
    public ComposicionProducida() {}

    public ComposicionProducida(Integer id, String canastillaCodigo, String canastillaDescripcion,
                                String articuloCodigo, String articuloDescripcion, int cantidad,
                                Boolean revisado, CanastillaProducida canastillaProducida) {
        this.id = id;
        this.canastillaCodigo = canastillaCodigo;
        this.canastillaDescripcion = canastillaDescripcion;
        this.articuloCodigo = articuloCodigo;
        this.articuloDescripcion = articuloDescripcion;
        this.cantidad = cantidad;
        this.revisado = revisado;
        this.canastillaProducida = canastillaProducida;
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

    public String getCanastillaCodigo() {
        return canastillaCodigo;
    }

    public void setCanastillaCodigo(String canastillaCodigo) {
        this.canastillaCodigo = canastillaCodigo;
    }

    public String getCanastillaDescripcion() {
        return canastillaDescripcion;
    }

    public void setCanastillaDescripcion(String canastillaDescripcion) {
        this.canastillaDescripcion = canastillaDescripcion;
    }

    public String getArticuloCodigo() {
        return articuloCodigo;
    }

    public void setArticuloCodigo(String articuloCodigo) {
        this.articuloCodigo = articuloCodigo;
    }

    public String getArticuloDescripcion() {
        return articuloDescripcion;
    }

    public void setArticuloDescripcion(String articuloDescripcion) {
        this.articuloDescripcion = articuloDescripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getRevisado() {
        return revisado;
    }

    public void setRevisado(Boolean revisado) {
        this.revisado = revisado;
    }

    public CanastillaProducida getCanastillaProducida() {
        return canastillaProducida;
    }

    public void setCanastillaProducida(CanastillaProducida canastillaProducida) {
        this.canastillaProducida = canastillaProducida;
    }
}
