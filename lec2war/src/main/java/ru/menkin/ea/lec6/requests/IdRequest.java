package ru.menkin.ea.lec6.requests;

import ru.menkin.ea.lec5.web.model.requests.Request;

public class IdRequest extends Request
{
	private Integer _id;

	// @formatter:off
	public Integer getId() { return _id; }
	public void setId(Integer id) { _id = id; }
	// @formatter:on
}
