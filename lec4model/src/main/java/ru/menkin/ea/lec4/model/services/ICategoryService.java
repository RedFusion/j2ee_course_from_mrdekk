package ru.menkin.ea.lec4.model.services;

import java.util.List;

import ru.menkin.ea.lec4.model.entities.Category;

public interface ICategoryService
{
	public Category create(Category category);

	public Category delete(int id) throws Exception;

	public List<Category> findAll();

	public Category update(Category category) throws Exception;

	public Category findById(int id);
}
