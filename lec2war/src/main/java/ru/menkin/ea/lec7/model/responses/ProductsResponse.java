package ru.menkin.ea.lec7.model.responses;

import java.util.ArrayList;
import java.util.List;

import ru.menkin.ea.lec7.model.entities.Product;

public class ProductsResponse extends Response
{
	private List<Product> _products = new ArrayList<Product>();

	// @formatter:off
	public List<Product> getProducts() { return _products; }
	public void setProducts(List<Product> products) { _products = products; }
	// @formatter:on
}