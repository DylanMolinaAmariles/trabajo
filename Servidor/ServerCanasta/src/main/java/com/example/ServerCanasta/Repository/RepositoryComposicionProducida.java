package com.example.ServerCanasta.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.ServerCanasta.modelo.ComposicionProducida;

/*
 * Repositorio de ComposicionProducida
 * Se encarga de enviar y recibir datos de la bbdd
 */
@Repository
public interface RepositoryComposicionProducida extends CrudRepository <ComposicionProducida, Integer> {
	 /**
     * Busca todas las composiciones asociadas a una canastilla por su ID.
     * Esto traduce a SQL: SELECT * FROM composiciones WHERE canastilla_id = ?
     */
    List<ComposicionProducida> findByCanastillaProducida_Id(Integer idCanastilla);

    /**
     * Busca una composición por ID de canastilla y código de artículo.
     * SQL: SELECT * FROM composiciones WHERE canastilla_id = ? AND articulo_codigo = ?
     */
	ComposicionProducida findByCanastillaProducida_IdAndArticuloCodigo(Integer idCanastilla, String ArticuloCodigo);
	
	@Query("SELECT c FROM ComposicionProducida c JOIN FETCH c.canastillaProducida")
	List<ComposicionProducida> findAllWithCanastilla();
	
	/**
     * Elimina las composiciones que ya fueron revisadas
     * y cuya fecha de canastilla es anterior a la indicada.
     */
	void deleteByRevisadoTrueAndCanastillaProducida_FechaSolicitudBefore(LocalDateTime fecha);
	
	/**
	 * Comprueba si una canastilla tiene todas la composiciones marcadas como revisadas.
	 * Devuelve true si no hay una composicion por revisar
	 */
	
	@Query("""
			SELECT CASE WHEN COUNT(c) = 0 THEN TRUE ELSE FALSE END
        FROM ComposicionProducida c
        WHERE c.canastillaProducida.id = :idCanastilla
        AND (c.revisado = false OR c.revisado IS NULL)
			""")
	
	boolean estaTotalmenteRevisada(Integer idcanastilla);
}
