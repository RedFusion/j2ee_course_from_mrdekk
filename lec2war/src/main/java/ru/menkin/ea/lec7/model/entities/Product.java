package ru.menkin.ea.lec7.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Product
{
	private String _name;
	private float _price;
	private float _quantity;
	private String _description;
	private List<String> _categories = new ArrayList<String>();

	// @formatter:off
	public String getName() {return _name;}
	public void setName(String name) {_name = name;}

	public String getDescription() {return _description;}
	public void setDescription(String description) {_description = description;}

	public float getPrice() {return _price;}
	public void setPrice(float price) {_price = price;}

	public float getQuantity() {return _quantity;}
	public void setQuantity(float quantity) {_quantity = quantity;}

	public List<String> getCategories() {	return _categories; }
	public void setCategories(List<String> categories) { _categories = categories;	}
	// @formatter:on

	public ru.menkin.ea.lec4.model.entities.Product create()
	{
		ru.menkin.ea.lec4.model.entities.Product product = new ru.menkin.ea.lec4.model.entities.Product();

		product.setId(null);
		product.setName(_name);
		product.setPrice(_price);
		product.setDescription(_description);
		product.setQuantity(_quantity);

		return product;
	}
}
