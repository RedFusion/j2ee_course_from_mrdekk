package ru.menkin.ea.lec4.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Warehouse")
public class Warehouse
{
	public Warehouse()
	{}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "capacity")
	private int capacity;

	@OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
	private List<Product> products = new ArrayList<Product>();

	@OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	// @formatter:off
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public int getCapacity() {return capacity;}
	public void setCapacity(int capacity) {this.capacity = capacity;}
	
	public List<Product> getProducts() {return products;}
	public void setProducts(List<Product> products) {this.products = products;}
	
	public List<OrderItem> getOrderItems() {return orderItems;}
	public void setOrderItems(List<OrderItem> orderItems) {this.orderItems = orderItems;}
	// @formatter:on
}
