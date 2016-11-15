package ru.menkin.ea.lec1.impl;

import java.util.HashMap;
import java.util.Map;

import ru.menkin.ea.lec1.interfaces.IProduct;

/**
 * @author menkin
 * @since 1.12.2015
 */

public class Warehouse extends BaseWarehouse {
	private static Map<IProduct, Double> _products;
	
	public Warehouse() {
		super();
		setProducts(new HashMap<IProduct, Double>());
	}

	public static Map<IProduct, Double> getProducts() {
		return _products;
	}

	public static void setProducts(Map<IProduct, Double> products) {
		_products = products;
	}
}

