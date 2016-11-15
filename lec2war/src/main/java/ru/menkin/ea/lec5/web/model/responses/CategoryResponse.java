package ru.menkin.ea.lec5.web.model.responses;

import ru.menkin.ea.lec5.web.model.entities.Category;

public class CategoryResponse extends Response
{
	private Category category;

	// @formatter:off
	public Category getCategory() { return category; }
	public void setCategory(Category category) { this.category = category; }
	// @formatter:on
}
