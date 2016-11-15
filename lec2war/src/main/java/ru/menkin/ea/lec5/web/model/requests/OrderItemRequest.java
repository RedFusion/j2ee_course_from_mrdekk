package ru.menkin.ea.lec5.web.model.requests;

import ru.menkin.ea.lec5.web.model.entities.OrderItem;

public class OrderItemRequest extends Request
{
	private OrderItem _orderItem;

	// @formatter:off
	public OrderItem getOrderItem() { return _orderItem; }
	public void setOrderItem(OrderItem orderItem) { _orderItem = orderItem; }
	// @formatter:on
}
