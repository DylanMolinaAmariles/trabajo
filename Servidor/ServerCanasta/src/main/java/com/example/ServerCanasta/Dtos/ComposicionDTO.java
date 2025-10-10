 package com.example.ServerCanasta.Dtos;

import lombok.Data;

@Data
public class ComposicionDTO {
	
	private Integer idCanastilla;
	private String codigoArticulo;
	private String descripcionArticulo;
	private int cantidad;
	private boolean revisado;
}
