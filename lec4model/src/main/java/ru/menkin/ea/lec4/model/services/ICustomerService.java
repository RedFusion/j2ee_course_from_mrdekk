package ru.menkin.ea.lec4.model.services;

import java.util.List;

import ru.menkin.ea.lec4.model.entities.Customer;

public interface ICustomerService
{
	public Customer create(Customer customer);

	public Customer delete(int id) throws Exception;

	public List<Customer> findAll();

	public Customer update(Customer customer) throws Exception;

	public Customer findById(int id);
}
