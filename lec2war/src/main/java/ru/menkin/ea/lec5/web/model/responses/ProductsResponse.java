package ru.menkin.ea.lec5.web.model.responses;

import java.util.ArrayList;
import java.util.List;

import ru.menkin.ea.lec5.web.model.entities.Product;

public class ProductsResponse extends Response
{
	private List<Product> products = new ArrayList<Product>();

	// @formatter:off
	public List<Product> getProducts() { return products; }
	public void setProducts(List<Product> products) { this.products = products; }
	// @formatter:on
}
