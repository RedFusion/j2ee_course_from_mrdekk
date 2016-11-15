package ru.menkin.ea.lec4.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.menkin.ea.lec4.model.entities.Warehouse;
import ru.menkin.ea.lec4.model.repositories.WarehouseRepository;

public class WarehouseService implements IWarehouseService
{
	@Autowired
	private WarehouseRepository warehouseRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Warehouse create(Warehouse warehouse)
	{
		return warehouseRepository.save(warehouse);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Warehouse delete(int id) throws Exception
	{
		Warehouse deletedWarehouse = warehouseRepository.findOne(id);

		if (null == deletedWarehouse)
		{
			throw new Exception("Warehouse not found");
		}
		warehouseRepository.delete(deletedWarehouse);
		return deletedWarehouse;
	}

	@Override
	public List<Warehouse> findAll()
	{
		return warehouseRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Warehouse update(Warehouse warehouse) throws Exception
	{
		Warehouse updatedWarehouse = warehouseRepository.findOne(warehouse.getId());

		if (null == updatedWarehouse)
		{
			throw new Exception("Warehouse not found");
		}

		updatedWarehouse.setName(warehouse.getName());
		updatedWarehouse.setCapacity(warehouse.getCapacity());
		updatedWarehouse.setProducts(warehouse.getProducts());
		updatedWarehouse.setOrderItems(warehouse.getOrderItems());

		warehouseRepository.save(updatedWarehouse);

		return updatedWarehouse;
	}

	@Override
	public Warehouse findById(int id)
	{
		return warehouseRepository.findOne(id);
	}

	// Lecture 5
	@Override
	public Warehouse findByName(String name)
	{
		List<Warehouse> warehouses = warehouseRepository.getWarehouseByName(name);
		if (warehouses == null || warehouses.isEmpty())
		{
			return null;
		}
		return warehouses.get(0);
	}

}
