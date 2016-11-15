package ru.menkin.ea.lec4.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderItem")
public class OrderItem
{
	public OrderItem()
	{}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_order", nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "id_product", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "id_warehouse", nullable = false)
	private Warehouse warehouse;

	@Column(name = "quantity", length = 45)
	private String quantity;

	// @formatter:off
	public Integer getId(){ return id; }
	public void setId(Integer id){ this.id = id;}

	public Order getOrder() { return order; }
	public void setOrder(Order order) { this.order = order; }

	public Product getProduct() { return product; }
	public void setProduct(Product product) { this.product = product; }

	public Warehouse getWarehouse() { return warehouse; }
	public void setWarehouse(Warehouse warehouse) {	this.warehouse = warehouse; }

	public String getQuantity() { return quantity; }
	public void setQuantity(String quantity) { this.quantity = quantity; }
	// @formatter:on
}
