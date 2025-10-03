package com.example.ServerCanasta.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ServerCanasta.modelo.CanastillaProducida;

/*
 * Repositorio de CanastillaProducida
 * Se encarga de enviar y recibir datos de la bbdd
 */
@Repository
public interface RepositoryCanastillaProducida extends CrudRepository<CanastillaProducida, Integer> {

	/**
     * Devuelve canastillas que tengan fecha anterior a la indicada.
     */
	List<CanastillaProducida> findByFechaSolicitudBefore(LocalDateTime fech);
}
