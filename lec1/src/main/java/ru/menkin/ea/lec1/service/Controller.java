package ru.menkin.ea.lec1.service;

import ru.menkin.ea.lec1.interfaces.ICategory;
import ru.menkin.ea.lec1.interfaces.IProduct;
import ru.menkin.ea.lec1.interfaces.IWarehouse;

public class Controller {
	
	private IWarehouse _warehouse;
	private IWarehouse _storage;
	private IProduct _product;
	private ICategory _category;
	private double _quantity;

	public IWarehouse getWarehouse() {
		return _warehouse;
	}

	public void setWarehouse(IWarehouse warehouse) {
		_warehouse = warehouse;
	}

	public IWarehouse getStorage() {
		return _storage;
	}

	public void setStorage(IWarehouse storage) {
		_storage = storage;
	}

	public IProduct getProduct() {
		return _product;
	}

	public void setProduct(IProduct product) {
		_product = product;
	}

	public ICategory getCategory() {
		return _category;
	}

	public void setCategory(ICategory category) {
		_category = category;
	}

	public double getQuantity() {
		return _quantity;
	}

	public void setQuantity(double quantity) {
		_quantity = quantity;
	}

	public Controller() {
		System.out.println("Work with Warehouse was started!");
	}

	public void doTheWork() {
		_warehouse.addProduct(_product, _quantity * 10);
		System.out.println(
				String.format("At the warehouse added product %s with price %s, " + "in the amount of %s units. ",
						_product.getName(), _product.getPrice(), _quantity));

		_warehouse.addProduct(_product, _quantity * (-2));
		System.out.println("Goods in warehouse " + _warehouse.listProducts());
		System.out.println(String.format("The balance of goods %s in warehouse is %s", _product.getName(),
				_warehouse.removeProduct(_product)));
		System.out.println("Work with Warehouse was finished");

		// Another IWarehouse implementation
		System.out.println("Work with Storage was started");
		_storage.addProduct(_product, _quantity);
		System.out.println(
				String.format("At the storage added product %s with price %s, " + "in the amount of %s units. ",
						_product.getName(), _product.getPrice(), _quantity));

		_storage.addProduct(_product, _quantity * (-0.5));
		System.out.println("Goods in storage " + _storage.listProducts());
		System.out.println(String.format("The balance of goods %s in storage is %s", _product.getName(),
				_storage.removeProduct(_product)));
		System.out.println("Work with Storage was finished");
	}
}
