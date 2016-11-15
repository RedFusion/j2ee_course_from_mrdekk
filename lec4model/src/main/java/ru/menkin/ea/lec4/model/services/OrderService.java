package ru.menkin.ea.lec4.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.menkin.ea.lec4.model.entities.Order;
import ru.menkin.ea.lec4.model.repositories.OrderRepository;

public class OrderService implements IOrderService
{
	@Autowired
	private OrderRepository orderRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Order create(Order order)
	{
		return orderRepository.save(order);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Order delete(int id) throws Exception
	{
		Order deletedOrder = orderRepository.findOne(id);

		if (deletedOrder == null)
		{
			throw new Exception("Order not found");
		}

		orderRepository.delete(deletedOrder);

		return deletedOrder;
	}

	@Override
	public List<Order> findAll()
	{
		return orderRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Order update(Order order) throws Exception
	{
		Order updatedOrder = orderRepository.findOne(order.getId());

		if (updatedOrder == null)
		{
			throw new Exception("Order not found");
		}

		updatedOrder.setCustomer(order.getCustomer());
		updatedOrder.setOrderItems(order.getOrderItems());

		orderRepository.save(updatedOrder);

		return updatedOrder;
	}

	@Override
	public Order findById(int id)
	{
		return orderRepository.findOne(id);
	}
}
