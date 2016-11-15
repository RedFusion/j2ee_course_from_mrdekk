package ru.menkin.ea.lec4.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.menkin.ea.lec4.model.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{

}