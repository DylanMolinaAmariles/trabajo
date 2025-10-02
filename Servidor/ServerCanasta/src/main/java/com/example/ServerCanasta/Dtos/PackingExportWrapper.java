package com.example.ServerCanasta.Dtos;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "PICKING_EXPORT")
public class PackingExportWrapper {
	
	@XmlElement(name = "PICKING_ITEM")
	private List<PickingItemDTO> items;

}
