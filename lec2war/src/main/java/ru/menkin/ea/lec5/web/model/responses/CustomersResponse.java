package ru.menkin.ea.lec5.web.model.responses;

import java.util.ArrayList;
import java.util.List;

import ru.menkin.ea.lec5.web.model.entities.Customer;

public class CustomersResponse extends Response
{
	private List<Customer> _customers = new ArrayList<Customer>();

	// @formatter:off
	public List<Customer> getCustomers() { return _customers; }
	public void setCustomers(List<Customer> customers) { _customers = customers; }
	// @formatter:on
}
