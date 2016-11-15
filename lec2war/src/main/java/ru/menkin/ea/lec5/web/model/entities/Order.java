package ru.menkin.ea.lec5.web.model.entities;

public class Order
{
	private Integer _id;

	// @formatter:off
	public Integer getId() { return _id; }
	public void setId(Integer id) { _id = id; }
	// @formatter:on

	public static Order map(ru.menkin.ea.lec4.model.entities.Order order)
	{
		Order ord = new Order();

		ord.setId(order.getId());

		return ord;
	}

	public ru.menkin.ea.lec4.model.entities.Order create()
	{
		ru.menkin.ea.lec4.model.entities.Order order = new ru.menkin.ea.lec4.model.entities.Order();

		order.setId(null);

		return order;
	}
	// update нет т.к. у нас всего 2 поля id и id_customer
}
