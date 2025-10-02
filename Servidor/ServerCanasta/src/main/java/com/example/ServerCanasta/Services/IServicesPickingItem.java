package com.example.ServerCanasta.Services;

import java.util.List;

import com.example.ServerCanasta.Dtos.PickingItemDTO;

public interface IServicesPickingItem {
	List<PickingItemDTO> generarPickingExport();
	void exportEmail(String email);
	
}
