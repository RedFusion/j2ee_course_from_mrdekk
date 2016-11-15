package ru.menkin.ea.lec4.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.menkin.ea.lec4.model.entities.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> 
{
	// Lecture 5
	@Query("select w from Warehouse w where w.name = :name")
	public List<Warehouse> getWarehouseByName(@Param("name") String name);
}
