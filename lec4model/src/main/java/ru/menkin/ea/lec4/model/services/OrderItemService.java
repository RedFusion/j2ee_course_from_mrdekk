package ru.menkin.ea.lec4.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.menkin.ea.lec4.model.entities.OrderItem;
import ru.menkin.ea.lec4.model.repositories.OrderItemRepository;

public class OrderItemService implements IOrderItemService
{
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OrderItem create(OrderItem orderItem)
	{
		return orderItemRepository.save(orderItem);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OrderItem delete(int id) throws Exception
	{
		OrderItem deletedOrderItem = orderItemRepository.findOne(id);

		if (deletedOrderItem == null)
		{
			throw new Exception("OrderItem not found");
		}

		orderItemRepository.delete(deletedOrderItem);

		return deletedOrderItem;
	}

	@Override
	public List<OrderItem> findAll()
	{
		return orderItemRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OrderItem update(OrderItem orderItem) throws Exception
	{
		OrderItem updatedOrderItem = orderItemRepository.findOne(orderItem.getId());

		if (updatedOrderItem == null)
		{
			throw new Exception("OrderItem not found");
		}

		updatedOrderItem.setOrder(orderItem.getOrder());
		updatedOrderItem.setProduct(orderItem.getProduct());
		updatedOrderItem.setQuantity(orderItem.getQuantity());
		updatedOrderItem.setWarehouse(orderItem.getWarehouse());

		orderItemRepository.save(updatedOrderItem);

		return updatedOrderItem;
	}

	@Override
	public OrderItem findById(int id)
	{
		return orderItemRepository.findOne(id);
	}

}
