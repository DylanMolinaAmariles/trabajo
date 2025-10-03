package com.example.ServerCanasta.Services;

import java.io.File;
import java.util.List;

import com.example.ServerCanasta.Dtos.PickingItemDTO;

public interface IServicesPickingItem {
	List<PickingItemDTO> generarPickingExport();
	
	void exportarXml(List<PickingItemDTO>items , File file) throws Exception;
	
}
