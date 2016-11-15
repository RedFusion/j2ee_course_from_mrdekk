package ru.menkin.ea.lec5.web.model.responses;

import ru.menkin.ea.lec5.web.model.entities.Product;

public class ProductResponse extends Response
{
	private Product product;

	// @formatter:off
	public Product getProduct() { return product; }
	public void setProduct(Product product) { this.product = product; }
	// @formatter:on
}