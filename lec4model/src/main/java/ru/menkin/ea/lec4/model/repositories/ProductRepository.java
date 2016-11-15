package ru.menkin.ea.lec4.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.menkin.ea.lec4.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
	// Lection 5
	@Query("select p from Product p where p.name = :name")
	public List<Product> getProductByName(@Param("name") String name);
}
