package ru.menkin.ea.lec5.web.model.responses;

import ru.menkin.ea.lec5.web.model.entities.Order;

public class OrderResponse extends Response
{
	private Order order;

	// @formatter:off
	public Order getOrder() { return order;	}
	public void setOrder(Order order) { this.order = order; }
	// @formatter:on
}
