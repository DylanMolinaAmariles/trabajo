package com.example.ServerCanasta.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerCanasta.Dtos.CanastillaDTO;
import com.example.ServerCanasta.Repository.RepositoryCanastillaProducida;
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

}
