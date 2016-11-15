package ru.menkin.ea.lec5.web.model.responses;

import ru.menkin.ea.lec5.web.model.entities.OrderItem;

public class OrderItemResponse extends Response
{
	private OrderItem orderItem;

	// @formatter:off
	public OrderItem getOrderItem() { return orderItem; }
	public void setOrderItem(OrderItem orderItem) { this.orderItem = orderItem; }
	// @formatter:on
}
