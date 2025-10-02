package com.example.ServerCanasta.Services;

import java.util.List;

import com.example.ServerCanasta.Dtos.CanastillaDTO;
import com.example.ServerCanasta.modelo.CanastillaProducida;

public interface IServicesCanastillaProducida {
	List<CanastillaDTO> listarCanastillas();
	CanastillaDTO obtenerCanastillaPorID(Integer id);
}
