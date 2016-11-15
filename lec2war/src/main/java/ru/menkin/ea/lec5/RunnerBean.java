package ru.menkin.ea.lec5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import ru.menkin.ea.lec4.model.entities.Category;
import ru.menkin.ea.lec4.model.entities.Customer;
import ru.menkin.ea.lec4.model.entities.Order;
import ru.menkin.ea.lec4.model.entities.OrderItem;
import ru.menkin.ea.lec4.model.entities.Product;
import ru.menkin.ea.lec4.model.entities.Warehouse;
import ru.menkin.ea.lec4.model.services.ICategoryService;
import ru.menkin.ea.lec4.model.services.ICustomerService;
import ru.menkin.ea.lec4.model.services.IOrderItemService;
import ru.menkin.ea.lec4.model.services.IOrderService;
import ru.menkin.ea.lec4.model.services.IProductService;
import ru.menkin.ea.lec4.model.services.IWarehouseService;

public class RunnerBean
{
	private static final Logger log = LoggerFactory.getLogger(RunnerBean.class);

	private ICategoryService _categoryService;
	private ICustomerService _customerService;
	private IProductService _productService;
	private IOrderService _orderService;
	private IOrderItemService _orderItemService;
	private IWarehouseService _warehouseService;

	// @formatter:off
	public ICategoryService getCategoryService() { return _categoryService;	}
	@Required
	public void setCategoryService(ICategoryService categoryService) { _categoryService = categoryService;	}
	
	public ICustomerService getCustomerService() { return _customerService; }
	@Required
	public void setCustomerService(ICustomerService customerService) { _customerService = customerService; }
	
	public IProductService getProductService() { return _productService; }
	@Required
	public void setProductService(IProductService productService) { _productService = productService; }
	
	public IOrderService getOrderService() { return _orderService; }
	@Required
	public void setOrderService(IOrderService orderService) { _orderService = orderService; }
	
	public IOrderItemService getOrderItemService() { return _orderItemService; }
	@Required
	public void setOrderItemService(IOrderItemService orderItemService) { _orderItemService = orderItemService; }
	
	public IWarehouseService getWarehouseService() { return _warehouseService; }
	@Required
	public void setWarehouseService(IWarehouseService warehouseService) { _warehouseService = warehouseService; }
	// @formatter:on

	public void init()
	{
		try
		{
			Warehouse warehouse = new Warehouse();
			warehouse.setName("Food Storage");
			warehouse.setCapacity(1000);
			warehouse = _warehouseService.create(warehouse);

			Category category = new Category();
			category.setName("food");
			category = _categoryService.create(category);

			Customer customer = new Customer();
			customer.setName("Client");
			customer = _customerService.create(customer);

			Order order = new Order();
			order.setCustomer(customer);
			order = _orderService.create(order);

			Product product = new Product();
			product.setName("flour");
			product.setQuantity(10.3f);
			product.setWarehouse(warehouse);
			product.setCategory(category);
			product.setDescription("Just cook");
			product.setPrice(50.5f);
			product = _productService.create(product);

			product = new Product();
			product.setName("flour");
			product.setQuantity(15.0f);
			product.setWarehouse(warehouse);
			product.setCategory(category);
			product.setDescription("Just cook");
			product.setPrice(47.9f);
			product = _productService.create(product);

			product = new Product();
			product.setName("sugar");
			product.setQuantity(90.9f);
			product.setWarehouse(warehouse);
			product.setCategory(category);
			product.setDescription("Just eat");
			product.setPrice(65.7f);
			product = _productService.create(product);

			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setQuantity("units");
			orderItem.setWarehouse(warehouse);
			orderItem = _orderItemService.create(orderItem);

			Product product4 = _productService.findById(product.getId());
			log.info(product4.toString());

			Product found = _productService.findByName("flour");
			if (found == null)
			{
				log.info("Продукт не найден. Что-то здесь не так...");
			} else
			{
				log.info(found.toString());
			}
		} catch (Exception exc)
		{
			log.error("Error", exc);
		}
	}
}
