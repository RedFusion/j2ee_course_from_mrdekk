package ru.menkin.ea.lec1.impl;

import java.util.Hashtable;
import java.util.Map;

import ru.menkin.ea.lec1.interfaces.IProduct;

public class Storage extends BaseWarehouse {
	private static Map<IProduct, Double> _products;
	
	public Storage() {
		super();
		setProducts(new Hashtable<IProduct, Double>());
	}

	public static Map<IProduct, Double> getProducts() {
		return _products;
	}

	public static void setProducts(Map<IProduct, Double> products) {
		_products = products;
	}
}
