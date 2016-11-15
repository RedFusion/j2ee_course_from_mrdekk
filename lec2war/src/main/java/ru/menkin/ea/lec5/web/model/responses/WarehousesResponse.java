package ru.menkin.ea.lec5.web.model.responses;

import java.util.ArrayList;
import java.util.List;

import ru.menkin.ea.lec5.web.model.entities.Warehouse;

public class WarehousesResponse extends Response
{
	private List<Warehouse> warehouses = new ArrayList<Warehouse>();

	// @formatter:off
	public List<Warehouse> getWarehouses() { return warehouses; }
	public void setWarehouses(List<Warehouse> warehouses) { this.warehouses = warehouses; }
	// @formatter:on
}
