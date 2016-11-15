package ru.menkin.ea.lec5.web.model.entities;

import ru.menkin.ea.lec5.utils.Comparer;

public class Category
{
	private Integer _id;
	private String _name;

	// @formatter:off
	public Integer getId() { return _id; }
	public void setId(Integer id) { _id = id; }
	
	public String getName() { return _name; }
	public void setName(String name) { _name = name; }
	// @formatter:on

	public static Category map(ru.menkin.ea.lec4.model.entities.Category category)
	{
		Category cat = new Category();

		cat.setId(category.getId());
		cat.setName(category.getName());
		/**
		 * ВАЖНО! Мы не занимаемся здесь отражением зависимых сущностей Это
		 * задача для контроллера в зависимости от типа метода
		 */
		return cat;
	}

	public ru.menkin.ea.lec4.model.entities.Category create()
	{
		ru.menkin.ea.lec4.model.entities.Category category = new ru.menkin.ea.lec4.model.entities.Category();

		// Потому что мы создаем новый, и хотим чтобы ID был сгенерирован в БД
		category.setId(null);
		category.setName(_name);

		return category;
	}

	public void update(ru.menkin.ea.lec4.model.entities.Category category)
	{
		if (!Comparer.equals(_name, category.getName()))
		{
			category.setName(_name);
		}
	}
}