package ru.menkin.ea.lec5.web.model.requests;

import ru.menkin.ea.lec5.web.model.entities.Category;
import ru.menkin.ea.lec5.web.model.entities.Product;

public class ProductRequest extends Request
{
	private Product _product;
	private Category _category;
	
	// @formatter:off
	public Product getProduct() { return _product; }
	public void setProduct(Product product) { this._product = product; }
	
	public Category getCategory(){ return _category; }
	public void setCategory(Category category) { _category = category; }
	// @formatter:on
	
}
