package com.example.ServerCanasta.Services;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerCanasta.Dtos.CanastillaDTO;
import com.example.ServerCanasta.Dtos.ComposicionDTO;
import com.example.ServerCanasta.Repository.RepositoryCanastillaProducida;
import com.example.ServerCanasta.Repository.RepositoryComposicionProducida;
import com.example.ServerCanasta.config.DtoConverter;
import com.example.ServerCanasta.modelo.CanastillaProducida;
import com.example.ServerCanasta.modelo.ComposicionProducida;

@Service
public class ServicesComposicionProducidaImpl implements IServicesComposicionProducida {
	
	/*
	 * Inyección del repositorio de ComposicionProducida
	 */
	@Autowired
	private RepositoryComposicionProducida composicionProducidaDAO;
	
	
	
	/*
	 * Inyección del convertidor para mapear Entidades <-> DTOs
	 */
	@Autowired
	private DtoConverter dtoConverter;
	
	/**
	 * Devuelve todas las composiciones almacenadas en BD como DTOs.
	 */
	@Override
	public List<ComposicionDTO> listaComposiciones() {
		List<ComposicionProducida> entidades = (List<ComposicionProducida>) composicionProducidaDAO.findAll();
		return dtoConverter.mapAll(entidades, ComposicionDTO.class);
	}

	/**
	 * Devuelve todas las composiciones que pertenecen a una canastilla.
	 */
	@Override
	public List<ComposicionDTO> listarComposicionesPorCanastilla(Integer idCanastilla) {
		List<ComposicionProducida> entidades = composicionProducidaDAO.findByCanastillaProducida_Id(idCanastilla);
		return dtoConverter.mapAll(entidades, ComposicionDTO.class);
	}

	/**
	 * Marca como revisado un artículo de una canastilla y descuenta -1 en cantidad.
	 */
	@Override
	public ComposicionDTO marcarRevisado(Integer idCanastilla, String articuloCodigo) {
		ComposicionProducida entidad = composicionProducidaDAO
				.findByCanastillaProducida_IdAndArticuloCodigo(idCanastilla, articuloCodigo);

		if (entidad != null) {
			entidad.setRevisado(true);
			entidad.setCantidad(entidad.getCantidad() - 1);
			composicionProducidaDAO.save(entidad);
			return dtoConverter.map(entidad, ComposicionDTO.class);
		}

		return null;
	}
	
	
}