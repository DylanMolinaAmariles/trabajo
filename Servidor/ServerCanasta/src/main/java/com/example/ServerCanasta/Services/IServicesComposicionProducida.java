package com.example.ServerCanasta.Services;

import java.util.List;

import com.example.ServerCanasta.Dtos.CanastillaDTO;
import com.example.ServerCanasta.Dtos.ComposicionDTO;

public interface IServicesComposicionProducida {
	List<ComposicionDTO> listaComposiciones();
	List<ComposicionDTO>listarComposicionesPorCanastilla(Integer idCanastilla);
	ComposicionDTO marcarRevisado(Integer idCanastilla, String srticuloCodigo);
	
	
}
