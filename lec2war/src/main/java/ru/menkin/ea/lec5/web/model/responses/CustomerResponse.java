package ru.menkin.ea.lec5.web.model.responses;

import ru.menkin.ea.lec5.web.model.entities.Customer;

public class CustomerResponse extends Response
{
	private Customer customer;

	// @formatter:off
	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }
	// @formatter:on
}
