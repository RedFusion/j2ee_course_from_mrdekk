package ru.menkin.ea.lec7.model.entities;

import java.net.InetAddress;

public class ProductHolder
{
	private String _hostname;

	// @formatter: off
	public String getHostname() { return _hostname; }
	public void setHostname(String hostname) { _hostname = hostname; }
	// @formatter: on
	
	public ProductHolder()
	{
		try
		{
			_hostname = InetAddress.getLocalHost().getHostName();
		} 
		catch (Exception e)
		{
			_hostname = "Unknown";
		}
	}
}
