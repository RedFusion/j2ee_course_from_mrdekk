package ru.menkin.ea.lec1.impl;

import ru.menkin.ea.lec1.interfaces.ICategory;

/**
 * @author menkin
 * @since 1.12.2015
 */
public class Category implements ICategory{
	private String _name;
	
	public Category(String name){
		_name = name;
	}
	
	@Override
	public String getName() {
		return _name;
	}
}
