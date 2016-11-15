package ru.menkin.ea.lec1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.menkin.ea.lec1.interfaces.IProduct;
import ru.menkin.ea.lec1.interfaces.IWarehouse;

public abstract class BaseWarehouse implements IWarehouse {
	private Map<IProduct, Double> _products = new HashMap<IProduct, Double>();

	private double _capacityWarehouse;
	private double _capacityCategory;

	public void setCapacityWarehouse(String capacityWarehouse) {
		_capacityWarehouse = Double.valueOf(capacityWarehouse);
	}

	public void setCapacityCategory(String capacityCategory) {
		_capacityCategory = Double.valueOf(capacityCategory);
	}

	@Override
	public void addProduct(IProduct product, double quantity) {
		double currentLoadWarehouse = calculateCurrentLoadWarehouse();
		double currentLoadCategory = calculateCurrentLoadCategory(product.getCategory().getName());

		double totalLoadWarehouse = currentLoadWarehouse + quantity;
		double totalLoadCategory = currentLoadCategory + quantity;
		
		boolean isNotOverload =((totalLoadWarehouse > _capacityWarehouse || 
				totalLoadCategory > _capacityCategory) || (currentLoadWarehouse!= 0 && 
				currentLoadCategory!= 0));
		if (isNotOverload) {
			System.out.println(String.format("Warehouse or category is Overloaded! Current load "
			+ "for warehouse is: %.1f for category is: %.1f",_capacityWarehouse, _capacityCategory));
			return;
		} else {
			if (!_products.containsKey(product)) {
				if (quantity > 0) {
					_products.put(product, quantity);
				} else {
					System.out.println(String.format("You can't put negative quantity goods in "
							+ "warehouse %s", product.getName()));
				}
			} else {
				if (_products.get(product) + quantity < 0) {
					System.out.println(String.format("Trying to remove to much (%s) product %s "
							+ "from warehouse, operation was cancelled",quantity, product.getName()));
					return;
				}
				_products.put(product, _products.get(product) + quantity);
			}
		}
	}

	@Override
	public double removeProduct(IProduct product) {
		double result = 0;
		if (!_products.containsKey(product)) {
			System.out.println(String.format("Goods %s is not in warehouse", product.getName()));
			result = 0;
		} else {
			result = _products.remove(product);
			System.out.println(String.format("Product %s was removed from warehouse", product.getName()));
		}
		return result;
	}

	@Override
	public List<String> listProducts() {
		List<String> list = new ArrayList<String>();
		for (Map.Entry<IProduct, Double> map : _products.entrySet()) {
			list.add(map.getKey().getName());
		}
		return list;
	}

	public double calculateCurrentLoadWarehouse() {
		double sum = 0;
		for (Map.Entry<IProduct, Double> pair : _products.entrySet()) {
			sum += pair.getValue();
		}
		return sum;
	}

	public double calculateCurrentLoadCategory(String category) {
		double sum = 0;
		for (Map.Entry<IProduct, Double> pair : _products.entrySet()) {
			if (pair.getKey().getCategory().equals(category)) {
				sum += pair.getValue();
			}
		}
		return sum;
	}
}
