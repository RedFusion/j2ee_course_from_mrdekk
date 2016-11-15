package ru.menkin.ea.lec5.web.model.responses;

import java.util.ArrayList;
import java.util.List;

import ru.menkin.ea.lec5.web.model.entities.OrderItem;

public class OrderItemsResponse extends Response
{
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	// @formatter:off
	public List<OrderItem> getOrderItems() { return orderItems; }
	public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
	// @formatter:on
}
