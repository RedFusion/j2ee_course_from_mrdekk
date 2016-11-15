package ru.menkin.ea.lec4.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product
{
	public Product()
	{}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_warehouse", nullable = false)
	private Warehouse warehouse;

	@Column(name = "name", nullable = true, length = 100)
	private String name;

	@ManyToOne
	@JoinColumn(name = "id_category", nullable = false)
	private Category category;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "price")
	private float price;

	@Column(name = "quantity")
	private float quantity;

	@Column(name = "discount")
	private float discount;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	// @formatter:off
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}

	public Warehouse getWarehouse() {return warehouse;}
	public void setWarehouse(Warehouse warehouse) {this.warehouse = warehouse;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public Category getCategory() {return category;}
	public void setCategory(Category category) {this.category = category;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	public float getPrice() {return price;}
	public void setPrice(float price) {this.price = price;}

	public float getQuantity() {return quantity;}
	public void setQuantity(float quantity) {this.quantity = quantity;}

	public float getDiscount() {return discount;}
	public void setDiscount(float discount) {this.discount = discount;}
	
	public List<OrderItem> getOrderItems() {return orderItems;}
	public void setOrderItems(List<OrderItem> orderItems) {this.orderItems = orderItems;}
	// @formatter:on
	
	@Override
	public String toString()
	{
		return name + " " + description + " " + price + " " + quantity;
	}
}
