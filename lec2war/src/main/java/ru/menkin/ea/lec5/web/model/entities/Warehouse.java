package ru.menkin.ea.lec5.web.model.entities;

import ru.menkin.ea.lec5.utils.Comparer;

public class Warehouse
{
	private Integer _id;
	private String _name;
	private int _capacity;

	// @formatter:off
	public Integer getId() { return _id; }
	public void setId(Integer id) { _id = id; }
	
	public String getName() { return _name; }
	public void setName(String name) { _name = name; }
	
	public int getCapacity() { return _capacity; }
	public void setCapacity(int capacity) { _capacity = capacity; }
	// @formatter:on

	public static Warehouse map(ru.menkin.ea.lec4.model.entities.Warehouse warehouse)
	{
		Warehouse wh = new Warehouse();

		wh.setId(warehouse.getId());
		wh.setName(warehouse.getName());
		wh.setCapacity(warehouse.getCapacity());

		return wh;
	}

	public ru.menkin.ea.lec4.model.entities.Warehouse create()
	{
		ru.menkin.ea.lec4.model.entities.Warehouse warehouse = new ru.menkin.ea.lec4.model.entities.Warehouse();

		warehouse.setId(null);
		warehouse.setName(_name);
		warehouse.setCapacity(_capacity);

		return warehouse;
	}

	public void update(ru.menkin.ea.lec4.model.entities.Warehouse warehouse)
	{
		if (!Comparer.equals(_name, warehouse.getName()))
		{
			warehouse.setName(_name);
		}
		if (!Comparer.equals(_capacity, warehouse.getCapacity()))
		{
			warehouse.setCapacity(_capacity);
		}
	}
}
