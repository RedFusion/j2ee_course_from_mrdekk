package ru.menkin.ea.lec7.model.requests;

import ru.menkin.ea.lec7.model.entities.ProductHolder;

public class ProductsRequest extends Request
{
	private ProductHolder _warehouse;

	// @formatter:off
	public ProductHolder getHolder() { return _warehouse; }
	public void setHolder(ProductHolder warehouse) { _warehouse = warehouse; }
	// @formatter:on
}

