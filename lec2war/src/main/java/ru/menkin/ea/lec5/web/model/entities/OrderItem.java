package ru.menkin.ea.lec5.web.model.entities;

import ru.menkin.ea.lec5.utils.Comparer;

public class OrderItem
{
	private Integer _id;
	private String _quantity;

	// @formatter:off
	public Integer getId() { return _id; }
	public void setId(Integer id) { _id = id; }
	
	public String getQuantity() { return _quantity; }
	public void setQuantity(String quantity) { _quantity = quantity; }
	// @formatter:on

	public static OrderItem map(ru.menkin.ea.lec4.model.entities.OrderItem orderItem)
	{
		OrderItem ord = new OrderItem();

		ord.setId(orderItem.getId());
		ord.setQuantity(orderItem.getQuantity());

		return ord;
	}

	public ru.menkin.ea.lec4.model.entities.OrderItem create()
	{
		ru.menkin.ea.lec4.model.entities.OrderItem order = new ru.menkin.ea.lec4.model.entities.OrderItem();

		order.setId(null);
		order.setQuantity(_quantity);

		return order;
	}

	public void update(ru.menkin.ea.lec4.model.entities.OrderItem order)
	{
		if (!Comparer.equals(_quantity, order.getQuantity()))
		{
			order.setQuantity(_quantity);
		}
	}

}
