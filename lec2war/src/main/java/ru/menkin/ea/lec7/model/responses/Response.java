package ru.menkin.ea.lec7.model.responses;

public class Response
{
	private String _status = "ok";

	//@ formatter: off
	public String getStatus() {	return _status; }
	public void setStatus(String status) {_status = status; }
	//@ formatter: on
}