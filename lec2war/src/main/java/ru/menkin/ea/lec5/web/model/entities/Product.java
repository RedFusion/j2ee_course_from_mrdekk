package ru.menkin.ea.lec5.web.model.entities;

import ru.menkin.ea.lec5.utils.Comparer;

public class Product
{
	private Integer _id;
	private String _name;
	private String _description;
	private float _price;
	private float _discount;
	private float _quantity;

	// @formatter:off
	public Integer getId() {return _id;}
	public void setId(Integer id) {_id = id;}

	public String getName() {return _name;}
	public void setName(String name) {_name = name;}

	public String getDescription() {return _description;}
	public void setDescription(String description) {_description = description;}

	public float getPrice() {return _price;}
	public void setPrice(float price) {_price = price;}

	public float getQuantity() {return _quantity;}
	public void setQuantity(float quantity) {_quantity = quantity;}

	public float getDiscount() {return _discount;}
	public void setDiscount(float discount) {_discount = discount;}
	// @formatter:on

	public static Product map(ru.menkin.ea.lec4.model.entities.Product product)
	{
		Product pro = new Product();

		pro.setId(product.getId());
		pro.setName(product.getName());
		pro.setPrice(product.getPrice());
		pro.setQuantity(product.getQuantity());
		pro.setDescription(product.getDescription());
		pro.setDiscount(product.getDiscount());

		return pro;
	}

	public ru.menkin.ea.lec4.model.entities.Product create()
	{
		ru.menkin.ea.lec4.model.entities.Product product = new ru.menkin.ea.lec4.model.entities.Product();

		product.setId(null);
		product.setName(_name);
		product.setPrice(_price);
		product.setDescription(_description);
		product.setDiscount(_discount);
		product.setQuantity(_quantity);

		return product;
	}

	public void update(ru.menkin.ea.lec4.model.entities.Product product)
	{
		if (!Comparer.equals(_name, product.getName()))
		{
			product.setName(_name);
		}
		if (!Comparer.equals(_price, product.getPrice()))
		{
			product.setPrice(_price);
		}
		if (!Comparer.equals(_quantity, product.getQuantity()))
		{
			product.setQuantity(_quantity);
		}
		if (!Comparer.equals(_description, product.getDescription()))
		{
			product.setDescription(_description);
		}
		if (!Comparer.equals(_discount, product.getDiscount()))
		{
			product.setDiscount(_discount);
		}
	}
}
