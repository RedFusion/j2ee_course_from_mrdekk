package ru.menkin.ea.lec5.web.model.responses;

import ru.menkin.ea.lec5.web.model.entities.Warehouse;

public class WarehouseResponse extends Response
{
	private Warehouse warehouse;

	// @formatter:off
	public Warehouse getWarehouse() { return warehouse; }
	public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }
	// @formatter:on
}
