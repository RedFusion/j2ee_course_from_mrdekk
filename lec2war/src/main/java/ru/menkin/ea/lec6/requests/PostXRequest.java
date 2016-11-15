package ru.menkin.ea.lec6.requests;

import ru.menkin.ea.lec5.web.model.requests.Request;

public class PostXRequest extends Request
{
	private String _str;

	// @formatter:off
	public String getStr() { return _str; }
	public void setStr(String str) { _str = str; }
	// @formatter:off
}
