package ru.menkin.ea.lec5.web.model.responses;

import java.util.ArrayList;
import java.util.List;

import ru.menkin.ea.lec5.web.model.entities.Order;

public class OrdersResponse extends Response
{
	private List<Order> orders = new ArrayList<Order>();

	// @formatter:off
	public List<Order> getOrders() { return orders; }
	public void setOrders(List<Order> orders) { this.orders = orders; }
	// @formatter:on
}
