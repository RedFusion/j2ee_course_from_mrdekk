package ru.menkin.ea.lec5.web.model.entities;

import ru.menkin.ea.lec5.utils.Comparer;

public class Customer
{
	private Integer _id;
	private String _name;

	// @formatter:off
	public Integer getId() { return _id; }
	public void setId(Integer id) { _id = id; }
	
	public String getName() { return _name; }
	public void setName(String name) { _name = name; }
	// @formatter:on

	public static Customer map(ru.menkin.ea.lec4.model.entities.Customer customer)
	{
		Customer cust = new Customer();

		cust.setId(customer.getId());
		cust.setName(customer.getName());

		return cust;
	}

	public ru.menkin.ea.lec4.model.entities.Customer create()
	{
		ru.menkin.ea.lec4.model.entities.Customer customer = new ru.menkin.ea.lec4.model.entities.Customer();

		customer.setId(null);
		customer.setName(_name);

		return customer;
	}

	public void update(ru.menkin.ea.lec4.model.entities.Customer customer)
	{
		if (!Comparer.equals(_name, customer.getName()))
		{
			customer.setName(_name);
		}
	}
}
