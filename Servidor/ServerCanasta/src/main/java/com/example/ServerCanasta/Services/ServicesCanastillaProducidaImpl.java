package com.example.ServerCanasta.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerCanasta.Dtos.CanastillaDTO;
import com.example.ServerCanasta.Repository.RepositoryCanastillaProducida;
import com.example.ServerCanasta.Repository.RepositoryComposicionProducida;
import com.example.ServerCanasta.config.DtoConverter;
import com.example.ServerCanasta.modelo.CanastillaProducida;
@Service
public class ServicesCanastillaProducidaImpl implements IServicesCanastillaProducida {
	
	/*
	 * Inyeccion de Repositorio de la canastillaProduida
	 * canastillaProducidaDAO variable que tiene el valor de repositorio
	 * para su uso 
	 */
	@Autowired
	private  RepositoryCanastillaProducida canastillaProducidaDAO;
	
	@Autowired
	private RepositoryComposicionProducida composicionProducidaDAO;
	
	//llamado al convertidor de dtos
	@Autowired
	private DtoConverter dtoConverter;
	
	/**
	 * Devuelve todas las canastillas almacenadas en BD como DTOs.
	 */
	@Override
	public List<CanastillaDTO> listarCanastillas() {
		// TODO Auto-generated method stub
		return dtoConverter.mapAll((List<CanastillaProducida>)canastillaProducidaDAO.findAll(), CanastillaDTO.class);
	}
	
	/**
	 * Devuelve la composicionProducida por id almacenada en BD como DTOs.
	 */
	@Override
	public CanastillaDTO obtenerCanastillaPorID(Integer id) {
		// TODO Auto-generated method stub
		return  dtoConverter.map(canastillaProducidaDAO.findById(id), CanastillaDTO.class);
	}
	
	/**
	 * Devuelve las canastillas que han sido revisadas 
	 */
	@Override
	public List<CanastillaDTO> listarCanastillasRevisadas() {
		// TODO Auto-generated method stub
		
		//Buscar todas las canastillas 
		Iterable<CanastillaProducida> iterable = canastillaProducidaDAO.findAll();
		List<CanastillaProducida> todas = new ArrayList<>();
		iterable.forEach(todas::add);
		
		 // Filtrar solo las que estén totalmente revisadas
        return todas.stream()
                .filter(c -> composicionProducidaDAO.estaTotalmenteRevisada(c.getId()))
                .map(c -> dtoConverter.map(c, CanastillaDTO.class))
                .collect(Collectors.toList());
	}

}
