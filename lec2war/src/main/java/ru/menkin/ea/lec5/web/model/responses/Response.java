package ru.menkin.ea.lec5.web.model.responses;

public class Response
{
	private String _status = "ok";

	// @formatter:off
	public void setStatus( String status ) { _status = status; }
	public String getStatus( ) { return _status; }
	// @formatter:on
}