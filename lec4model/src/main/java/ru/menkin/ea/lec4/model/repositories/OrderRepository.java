package ru.menkin.ea.lec4.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.menkin.ea.lec4.model.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>
{

}
