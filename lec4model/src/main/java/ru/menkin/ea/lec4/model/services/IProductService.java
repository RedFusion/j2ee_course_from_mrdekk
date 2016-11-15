package ru.menkin.ea.lec4.model.services;

import java.util.List;

import ru.menkin.ea.lec4.model.entities.Product;

public interface IProductService
{
	public Product create(Product product);

	public Product delete(int id) throws Exception;

	public List<Product> findAll();

	public Product update(Product product) throws Exception;

	public Product findById(int id);

	// Lecture 5
	public Product findByName(String name);
}
