package ru.menkin.ea.lec6.responses;

import ru.menkin.ea.lec5.web.model.responses.Response;

public class AjaxResponse extends Response
{
	private String _str;

	// @formatter:off
	public String getStr() { return _str; }
	public void setStr(String str) { _str = str; }
	// @formatter:off
}
