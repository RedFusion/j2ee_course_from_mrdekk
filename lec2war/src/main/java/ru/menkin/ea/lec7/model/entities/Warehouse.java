package ru.menkin.ea.lec7.model.entities;

import java.util.ArrayList;
import java.util.List;

import ru.menkin.ea.lec4.model.entities.Product;

public class Warehouse
{
	private Integer _id;
	private String _name;
	private int _capacity;
	private List<Product> _products = new ArrayList<Product>();
	
	// @formatter:off
	public Integer getId() { return _id; }
	public void setId(Integer id) { _id = id; }
	
	public String getName() { return _name; }
	public void setName(String name) { _name = name; }
	
	public int getCapacity() { return _capacity; }
	public void setCapacity(int capacity) { _capacity = capacity; }
	
	public List<Product> getProducts() { return _products; }
	public void setProducts(List<Product> products) { _products = products; }
	// @formatter:on
	
	public ru.menkin.ea.lec4.model.entities.Warehouse create()
	{
		ru.menkin.ea.lec4.model.entities.Warehouse warehouse = new ru.menkin.ea.lec4.model.entities.Warehouse();

		warehouse.setId(null);
		warehouse.setName(_name);
		warehouse.setCapacity(_capacity);

		return warehouse;
	}
	
}