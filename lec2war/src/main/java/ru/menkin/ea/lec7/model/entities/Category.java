package ru.menkin.ea.lec7.model.entities;

import java.util.ArrayList;
import java.util.List;

import ru.menkin.ea.lec4.model.entities.Product;

public class Category
{
	private String _name;
	private List<Product> _products = new ArrayList<Product>();
	
	// @formatter:off
	public String getName() { return _name; }
	public void setName(String _name) {	this._name = _name;	}
	
	public List<Product> getProducts() { return _products; }
	public void setProducts(List<Product> products) { _products = products; }
	// @formatter:on
	
	public ru.menkin.ea.lec4.model.entities.Category create()
	{
		ru.menkin.ea.lec4.model.entities.Category category = new ru.menkin.ea.lec4.model.entities.Category();

		// Потому что мы создаем новый, и хотим чтобы ID был сгенерирован в БД
		category.setId(null);
		category.setName(_name);

		return category;
	}
}