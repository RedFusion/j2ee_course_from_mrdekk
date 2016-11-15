package ru.menkin.ea.lec3.jndi;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

public class CapacityWarehouse implements Referenceable {
	public static final String REF_ADDR_REF = "name";
	public static final String REF_ADDR_CAPACITY_WAREHOUSE = "capacityWarehouse";
	public static final String REF_ADDR_CAPACITY_CATEGORY = "capacityCategory";
	public static final String REF_ADDR_UPDATE_TIME = "time";
	
	private String _name;
	private double _capacityWarehouse;
	private double _capacityCategory;
	private int _time;

	public CapacityWarehouse(String name, double capacity, double capacityCategory, int time) {
		_name = name;
		_capacityWarehouse = capacity;
		_capacityCategory = capacityCategory;
		_time = time;
	}

	public Reference getReference() throws NamingException {
		Reference ref = 
				new Reference(CapacityWarehouse.class.getName(), 
				new StringRefAddr("name", _name),
				CapacityWarehouseFactory.class.getName(), null);

		ref.add(new StringRefAddr("capacityWarehouse", Double.toString(_capacityWarehouse)));
		ref.add(new StringRefAddr("capacityCategory", Double.toString(_capacityCategory)));
		ref.add(new StringRefAddr("time", Integer.toString(_time)));
		return ref;
	}
	
	public String toString() {
		return _name;
	}

	public String getName() {
		return _name;
	}

	public double getCapacityWarehouse() {
		return _capacityWarehouse;
	}
	
	public double getCapacityCategory() {
		return _capacityCategory;
	}
	
	public int getUpdateTime() {
		return _time;
	}
}