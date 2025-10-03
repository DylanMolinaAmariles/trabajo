package com.example.ServerCanasta.Dtos;

import lombok.Data;

@Data
public class CanastillaDTO {

	private Integer id;
	private String codigo;
	private String descripcion;
	private String nombrePadres;
	private String fechaSolicitud;
	private Double precio;
	private boolean revisada;
}
