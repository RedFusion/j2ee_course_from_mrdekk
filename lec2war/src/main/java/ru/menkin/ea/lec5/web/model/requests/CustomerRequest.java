package ru.menkin.ea.lec5.web.model.requests;

import ru.menkin.ea.lec5.web.model.entities.Customer;

public class CustomerRequest extends Request
{
	private Customer _customer;

	// @formatter:off
	public Customer getCustomer() { return _customer; }
	public void setCustomer(Customer customer) { _customer = customer; }
	// @formatter:off
}
