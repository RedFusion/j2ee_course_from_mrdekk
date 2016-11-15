package ru.menkin.ea.lec5.web.model.requests;

import ru.menkin.ea.lec5.web.model.entities.Category;

public class CategoryRequest extends Request
{
	private Category _category;

	// @formatter:off
	public Category getCategory() { return _category; }
	public void setCategory(Category category) { _category = category; }
	// @formatter:on
}
