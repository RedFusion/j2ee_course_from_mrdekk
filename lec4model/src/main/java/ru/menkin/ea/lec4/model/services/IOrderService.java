package ru.menkin.ea.lec4.model.services;

import java.util.List;

import ru.menkin.ea.lec4.model.entities.Order;

public interface IOrderService
{
	public Order create(Order order);

	public Order delete(int id) throws Exception;

	public List<Order> findAll();

	public Order update(Order order) throws Exception;

	public Order findById(int id);
}
