package ru.menkin.ea.lec3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import ru.menkin.ea.lec1.interfaces.ICategory;
import ru.menkin.ea.lec1.interfaces.IProduct;
import ru.menkin.ea.lec1.interfaces.IWarehouse;
import ru.menkin.ea.lec3.jndi.CapacityWarehouse;

public class RunnerBean {
	private static final Logger log = LoggerFactory.getLogger(RunnerBean.class);

	private CapacityWarehouse _capacityWarehouse;
	private IWarehouse _warehouse;
	private IProduct _product;
	private ICategory _category;
	private double _quantity;

	// @formatter:off
	@Required
	public void setCapacityWarehouse(CapacityWarehouse capacityWarehouse) {
		_capacityWarehouse = capacityWarehouse;
	}

	public CapacityWarehouse getCapacityWarehouse() {
		return _capacityWarehouse;
	}
	// @formatter:on

	public IWarehouse getWarehouse() {
		return _warehouse;
	}

	public void setWarehouse(IWarehouse warehouse) {
		_warehouse = warehouse;
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

	public void init() {
		log.info(String.format(
				"For storage <%s> configured Total capacity in : "
						+ "%s units and limit capacity for each category in: %s units",
				_capacityWarehouse.getName(), _capacityWarehouse.getCapacityWarehouse(),
				_capacityWarehouse.getCapacityCategory()));
		log.info(String.format("The data will be updated every %s seconds", _capacityWarehouse.getUpdateTime() / 1000));

		log.info("Work with Warehouse was started!");
		_warehouse.addProduct(_product, _quantity);
		log.info(String.format("At the warehouse added product %s with price %s, " + "in the amount of %s units. ",
				_product.getName(), _product.getPrice(), _quantity));

		_warehouse.addProduct(_product, _quantity * (-2));
		log.info("Goods in warehouse " + _warehouse.listProducts());
		log.info(String.format("The balance of goods %s in warehouse is %s", _product.getName(),
				_warehouse.removeProduct(_product)));
		log.info("Work with Warehouse was finished");
	}
}