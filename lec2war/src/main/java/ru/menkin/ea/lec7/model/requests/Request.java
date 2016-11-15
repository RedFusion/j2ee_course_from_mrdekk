package ru.menkin.ea.lec7.model.requests;

public class Request
{
	private String _type;
	private int _limit;
	
	// @formatter:off
	public String getType() { return _type;	} 
	public void setType(String type) { _type = type; }
	
	public int getLimit() { return _limit; }
	public void setLimit(int limit) { _limit = limit; }
	// @formatter:on
}
