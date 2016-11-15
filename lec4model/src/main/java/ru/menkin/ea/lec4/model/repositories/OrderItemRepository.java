package ru.menkin.ea.lec4.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.menkin.ea.lec4.model.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>
{

}
