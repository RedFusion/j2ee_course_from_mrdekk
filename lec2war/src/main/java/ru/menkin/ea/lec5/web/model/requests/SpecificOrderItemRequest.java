package ru.menkin.ea.lec5.web.model.requests;

import ru.menkin.ea.lec5.web.model.entities.OrderItem;
import ru.menkin.ea.lec5.web.model.entities.Product;
import ru.menkin.ea.lec5.web.model.entities.Warehouse;

public class SpecificOrderItemRequest extends Request
{
	private OrderItem _orderItem;
	private Product _product;
	private Warehouse _warehouse;

	// @formatter:off
	public OrderItem getOrderItem() { return _orderItem; }
	public void setOrderItem(OrderItem orderItem) { _orderItem = orderItem; }
	
	public Product getProduct(){ return _product; }
	public void setProduct(Product product) { _product = product; }
	
	public Warehouse getWarehouse() { return _warehouse; }
	public void setWarehouse(Warehouse warehouse) { _warehouse = warehouse; }
	// @formatter:on
}
