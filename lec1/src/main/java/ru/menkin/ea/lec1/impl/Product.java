package ru.menkin.ea.lec1.impl;

import ru.menkin.ea.lec1.interfaces.ICategory;
import ru.menkin.ea.lec1.interfaces.IProduct;

/**
 * @author menkin
 * @since 1.12.2015
 */
public class Product implements IProduct{
	private String _name;
	private double _price;
	private Category _category;
	
	public Product(String name, double price, Category category){
		_name = name;
		_price = price;
		_category = category;
	}
	
	@Override
	public String getName() {
		return _name;
	}

	@Override
	public ICategory getCategory() {
		return _category;
	}

	@Override
	public double getPrice() {
		return _price;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Product){
			return (_name.equals(((Product) o).getName()) && _price == ((Product) o).getPrice());
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return _name.hashCode();
	}
}
