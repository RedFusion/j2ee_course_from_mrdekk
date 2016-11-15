package ru.menkin.ea.lec6.responses;

import ru.menkin.ea.lec5.web.model.responses.Response;

public class IdResponse extends Response
{
	private Integer _id;

	// @formatter:off
	public Integer getId() { return _id; }
	public void setId(Integer id) { _id = id; }
	// @formatter:on
}
