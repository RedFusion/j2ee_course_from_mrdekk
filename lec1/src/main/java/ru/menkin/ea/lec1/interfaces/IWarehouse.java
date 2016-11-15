package ru.menkin.ea.lec1.interfaces;

import java.util.List;

public interface IWarehouse {
	void addProduct(IProduct product, double quantity);
	double removeProduct(IProduct product);
	List<String> listProducts();
}