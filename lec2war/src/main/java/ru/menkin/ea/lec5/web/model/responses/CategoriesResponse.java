package ru.menkin.ea.lec5.web.model.responses;

import java.util.ArrayList;
import java.util.List;

import ru.menkin.ea.lec5.web.model.entities.Category;

public class CategoriesResponse extends Response
{
	private List<Category> categories = new ArrayList<>();

	// @formatter:off
	public List<Category> getCategories() { return categories; }
	public void setCategories(List<Category> categories) { this.categories = categories; }
	// @formatter:on
}
