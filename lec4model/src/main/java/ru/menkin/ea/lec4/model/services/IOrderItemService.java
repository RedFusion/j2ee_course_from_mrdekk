package ru.menkin.ea.lec4.model.services;

import java.util.List;

import ru.menkin.ea.lec4.model.entities.OrderItem;

public interface IOrderItemService
{
	public OrderItem create(OrderItem orderItem);

	public OrderItem delete(int id) throws Exception;

	public List<OrderItem> findAll();

	public OrderItem update(OrderItem orderItem) throws Exception;

	public OrderItem findById(int id);
}
