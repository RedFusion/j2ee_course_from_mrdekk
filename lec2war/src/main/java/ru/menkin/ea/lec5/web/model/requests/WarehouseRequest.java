package ru.menkin.ea.lec5.web.model.requests;

import ru.menkin.ea.lec5.web.model.entities.Warehouse;

public class WarehouseRequest extends Request
{
	private Warehouse _warehouse;

	// @formatter:off
	public Warehouse getWarehouse() { return _warehouse; }
	public void setWarehouse(Warehouse warehouse) { _warehouse = warehouse; }
	// @formatter:on
}
