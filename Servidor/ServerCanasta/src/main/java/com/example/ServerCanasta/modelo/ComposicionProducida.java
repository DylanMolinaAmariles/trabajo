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


//Lombok 
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
  
}
