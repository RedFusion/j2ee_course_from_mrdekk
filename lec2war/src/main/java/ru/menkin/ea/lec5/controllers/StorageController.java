package ru.menkin.ea.lec5.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.menkin.ea.lec4.model.services.ICategoryService;
import ru.menkin.ea.lec4.model.services.ICustomerService;
import ru.menkin.ea.lec4.model.services.IOrderItemService;
import ru.menkin.ea.lec4.model.services.IOrderService;
import ru.menkin.ea.lec4.model.services.IProductService;
import ru.menkin.ea.lec4.model.services.IWarehouseService;
import ru.menkin.ea.lec5.web.model.entities.Category;
import ru.menkin.ea.lec5.web.model.entities.Customer;
import ru.menkin.ea.lec5.web.model.entities.Order;
import ru.menkin.ea.lec5.web.model.entities.OrderItem;
import ru.menkin.ea.lec5.web.model.entities.Product;
import ru.menkin.ea.lec5.web.model.entities.Warehouse;
import ru.menkin.ea.lec5.web.model.requests.CustomerRequest;
import ru.menkin.ea.lec5.web.model.requests.OrderItemRequest;
import ru.menkin.ea.lec5.web.model.requests.OrderRequest;
import ru.menkin.ea.lec5.web.model.requests.ProductRequest;
import ru.menkin.ea.lec5.web.model.requests.SpecificOrderItemRequest;
import ru.menkin.ea.lec5.web.model.requests.WarehouseRequest;
import ru.menkin.ea.lec5.web.model.responses.CategoriesResponse;
import ru.menkin.ea.lec5.web.model.responses.CustomerResponse;
import ru.menkin.ea.lec5.web.model.responses.CustomersResponse;
import ru.menkin.ea.lec5.web.model.responses.IdResponse;
import ru.menkin.ea.lec5.web.model.responses.OrderItemResponse;
import ru.menkin.ea.lec5.web.model.responses.OrderItemsResponse;
import ru.menkin.ea.lec5.web.model.responses.OrderResponse;
import ru.menkin.ea.lec5.web.model.responses.OrdersResponse;
import ru.menkin.ea.lec5.web.model.responses.ProductResponse;
import ru.menkin.ea.lec5.web.model.responses.ProductsResponse;
import ru.menkin.ea.lec5.web.model.responses.Response;
import ru.menkin.ea.lec5.web.model.responses.WarehouseResponse;
import ru.menkin.ea.lec5.web.model.responses.WarehousesResponse;

@Controller
@RequestMapping(value = "/rest")
public class StorageController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(StorageController.class);
	
	@Autowired
	@Qualifier("categoryService")
	private ICategoryService _categoryService;

	@Autowired
	@Qualifier("customerService")
	private ICustomerService _customerService;

	@Autowired
	@Qualifier("productService")
	private IProductService _productService;

	@Autowired
	@Qualifier("orderService")
	private IOrderService _orderService;

	@Autowired
	@Qualifier("orderItemService")
	private IOrderItemService _orderItemService;

	@Autowired
	@Qualifier("warehouseService")
	private IWarehouseService _warehouseService;

	//Tested
	// получить все категории
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody CategoriesResponse getCategories() throws Exception
	{
		List<ru.menkin.ea.lec4.model.entities.Category> dbCategory = _categoryService.findAll();
		if (dbCategory.isEmpty())
		{
			throw new Exception("Нет ни одной категории!");
		}

		CategoriesResponse response = new CategoriesResponse();
		for (ru.menkin.ea.lec4.model.entities.Category cat : dbCategory)
		{
			Category category = Category.map(cat);
			if (category != null)
			{
				response.getCategories().add(category);
			}
		}
		return response;
	}
	
	//Tested
	//1 Запросить информацию об ассортименте на складе 
	@RequestMapping(value = "/warehouse/{warehouseId}/products", method = RequestMethod.GET)
	public @ResponseBody ProductsResponse getProductsInWarehouse(
			@PathVariable("warehouseId") Integer id) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Warehouse dbWarehouse = findWarehouse(id);

		ProductsResponse response = new ProductsResponse();
		for (ru.menkin.ea.lec4.model.entities.Product dbProduct : dbWarehouse.getProducts())
		{
			Product product = Product.map(dbProduct);
			if (product != null)
			{
				response.getProducts().add(product);
			}
		}
		return response;
	}
	
	//	{"product": 
	//    { "name": "potato",
	//      "description": "Rest add",
	//      "price": 50.5,
	//      "quantity": 10.3,
	//      "discount": 10.0
	//    }}
	//Tested
	//2 Добавить товар на конкретный склад склад, в нужную категорию 
	@RequestMapping(value = "/warehouse/{warehouseId}/categories/{categoryId}/product", method = RequestMethod.POST)
	public @ResponseBody IdResponse createProductInWarehouse(
			@PathVariable("warehouseId") Integer id,
			@PathVariable("categoryId") Integer catId, 
			@RequestBody ProductRequest request) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Warehouse dbWarehouse = findWarehouse(id);
		ru.menkin.ea.lec4.model.entities.Category dbCategory = findCategory(catId);

		ru.menkin.ea.lec4.model.entities.Product dbProduct = request.getProduct().create();
		dbProduct.setWarehouse(dbWarehouse);
		dbProduct.setCategory(dbCategory);

		dbProduct = _productService.create(dbProduct);

		IdResponse response = new IdResponse();
		response.setId(dbProduct.getId());

		return response;
	}
	
//		{"product": 
//	    { "name": "cheese",
//	      "description": "Rest another",
//	      "price": 2500.5,
//	      "quantity": 10.3,
//	      "discount": 10.0
//	    },
//		  "category": { "id" : 2}
//	  }
	//2 второй вариант без @PathVariable("categoryId")
	@RequestMapping(value = "/warehouse/{warehouseId}/product", method = RequestMethod.POST)
	public @ResponseBody IdResponse createProduct(
			@PathVariable("warehouseId") Integer id,
			@RequestBody ProductRequest request) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Warehouse dbWarehouse = findWarehouse(id);
			
		Integer catId = request.getCategory().getId();
		ru.menkin.ea.lec4.model.entities.Category dbCategory = findCategory(catId);

		ru.menkin.ea.lec4.model.entities.Product dbProduct = request.getProduct().create();
		dbProduct.setWarehouse(dbWarehouse);
		dbProduct.setCategory(dbCategory);

		dbProduct = _productService.create(dbProduct);

		IdResponse response = new IdResponse();
		response.setId(dbProduct.getId());

		return response;
	}
	
	//Tested
	//3 Удалить товар со склада
	@RequestMapping(value = "/warehouse/{warehouseId}/products/{productId}", method = RequestMethod.DELETE)
	public @ResponseBody Response deleteProductFromWarehouse(
			@PathVariable("warehouseId") Integer id,
			@PathVariable("productId") Integer prodId) throws Exception
	{
		// Сначала проверяем, что такой продукт существует
		findProduct(id, prodId);
		_productService.delete(prodId);

		return new Response();
	}
	
	//	    {"product": 
	//	      { "name": "potato",
	//	        "description": "Rest edit",
	//	        "price": 50.5,
	//	        "quantity": 10.3,
	//	        "discount": 10.0 }
	//		}
	//Tested
	//4 Изменить параметры товара на складе 
	@RequestMapping(value = "/warehouse/{warehouseId}/products/{productId}", method = RequestMethod.PUT)
	public @ResponseBody Response updateProductInWarehouse(
			@PathVariable("warehouseId") Integer id,
			@PathVariable("productId") Integer prodId, 
			@RequestBody ProductRequest request) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Product dbProduct = findProduct(id, prodId);

		request.getProduct().update(dbProduct);
		_productService.update(dbProduct);

		return new Response();
	}

	//	{"order":{}}
	//Tested
	//5 Разместить заказ
	@RequestMapping(value = "/customer/{customerId}/orders", method = RequestMethod.POST)
	public @ResponseBody IdResponse createOrderForCustomer(
			@PathVariable("customerId") Integer id,
			@RequestBody OrderRequest request) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Customer dbCustomer = findCustomer(id);

		ru.menkin.ea.lec4.model.entities.Order dbOrder = request.getOrder().create();
		dbOrder.setCustomer(dbCustomer);

		dbOrder = _orderService.create(dbOrder);

		IdResponse response = new IdResponse();
		response.setId(dbOrder.getId());

		return response;
	}
	
	//Tested
	//7 Отменить заказ
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.DELETE)
	public @ResponseBody Response deleteOrder(@PathVariable("orderId") Integer id) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Order dbOrder = _orderService.findById(id);

		if (dbOrder == null)
		{
			throw new Exception(String.format("Заказ с идентификатором %d не найден", id));
		}
		_orderService.delete(id);

		return new Response();
	}
	
	//Tested
	//8 Получить заказы конкретного клиента
	@RequestMapping(value = "customers/{customerId}/orders", method = RequestMethod.GET)
	public @ResponseBody OrdersResponse getOrdersCustomer(
			@PathVariable("customerId") Integer id) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Customer dbCustomer = findCustomer(id);

		OrdersResponse response = new OrdersResponse();
		for (ru.menkin.ea.lec4.model.entities.Order dbOrder : dbCustomer.getOrders())
		{
			Order order = Order.map(dbOrder);
			if (order != null)
			{
				response.getOrders().add(order);
			}
		}
		return response;
	}
	
	//Tested
	//9 Отменить конкретный заказ конкретного клиента
	@RequestMapping(value = "/customers/{customerId}/orders/{orderId}", method = RequestMethod.DELETE)
	public @ResponseBody Response deleteOrderCustomer(
			@PathVariable("customerId") Integer id,
			@PathVariable("orderId") Integer orderId) throws Exception
	{
		// Сначала проверяем, что такой заказ существует
		findOrder(id, orderId);
		_orderService.delete(orderId);

		return new Response();
	}
	
	//Tested
	//1* Получить список всех складов	/warehouse GET
	@RequestMapping(value = "/warehouse", method = RequestMethod.GET)
	public @ResponseBody WarehousesResponse getWarehouses() throws Exception
	{
		List<ru.menkin.ea.lec4.model.entities.Warehouse> dbWarehouse = _warehouseService.findAll();
		if (dbWarehouse.isEmpty())
		{
			throw new Exception("Нет ни одного склада");
		}

		WarehousesResponse response = new WarehousesResponse();
		for (ru.menkin.ea.lec4.model.entities.Warehouse war : dbWarehouse)
		{
			Warehouse warehouse = Warehouse.map(war);
			if (warehouse != null)
			{
				response.getWarehouses().add(warehouse);
			}
		}
		return response;
	}
	
	//Tested Raw Body {"warehouse":{"name":"newWarehouse","capacity":1000}}
	//1* Добавить склад	/warehouse POST
	@RequestMapping(value = "/warehouse", method = RequestMethod.POST)
	public @ResponseBody IdResponse createWarehouse(@RequestBody WarehouseRequest request) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Warehouse dbWarehouse = request.getWarehouse().create();

		dbWarehouse = _warehouseService.create(dbWarehouse);

		IdResponse response = new IdResponse();
		response.setId(dbWarehouse.getId());

		return response;
	}
	
	//Tested
	//2* GET получить информацию о конкретном складе
	@RequestMapping(value = "/warehouse/{warehouseId}", method = RequestMethod.GET)
	public @ResponseBody WarehouseResponse getWarehouse(@PathVariable("warehouseId") Integer id) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Warehouse dbWarehouse = findWarehouse(id);

		WarehouseResponse response = new WarehouseResponse();
		Warehouse warehouse = Warehouse.map(dbWarehouse);
		if (warehouse != null)
		{
			response.setWarehouse(warehouse);
		}

		return response;
	}
	
	//	{
	//	    "warehouse": 
	//		{
	//	        "name": "PCWarehouse",
	//	        "capacity": 50000
	//	    }
	//	}
	//Tested
	//2* PUT изменить имя и емкость склада
	@RequestMapping(value = "/warehouse/{warehouseId}", method = RequestMethod.PUT)
	public @ResponseBody Response updateWarehouse(
			@PathVariable("warehouseId") Integer id,
			@RequestBody WarehouseRequest request) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Warehouse dbWarehouse = findWarehouse(id);
		request.getWarehouse().update(dbWarehouse);
		_warehouseService.update(dbWarehouse);

		return new Response();
	}
	
	//Tested
	//2* DELETE удалить склад
	@RequestMapping(value = "/warehouse/{warehouseId}", method = RequestMethod.DELETE)
	public @ResponseBody Response deleteWarehouse(
			@PathVariable("warehouseId") Integer id) throws Exception
	{
		findWarehouse(id);
		_warehouseService.delete(id);
		return new Response();
	}
	
	//Tested
	//4* GET Получить инфо по конкретному продукту   PUT DELETE - есть
	@RequestMapping(value = "/warehouse/{warehouseId}/products/{productId}", method = RequestMethod.GET)
	public @ResponseBody ProductResponse getProduct(
			@PathVariable("warehouseId") Integer id,
			@PathVariable("productId") Integer prodId) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Product dbProduct = findProduct(id, prodId);

		ProductResponse response = new ProductResponse();
		Product product = Product.map(dbProduct);
		if (product != null)
		{
			response.setProduct(product);
		}
		return response;
	}

	//Tested
	// 5* /customers GET информация о всех клиентах
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public @ResponseBody CustomersResponse getCustomers() throws Exception
	{
		List<ru.menkin.ea.lec4.model.entities.Customer> dbCustomers = _customerService.findAll();
		if (dbCustomers.isEmpty())
		{
			throw new Exception("Нет ни одного клиента - это плохо! Надо что-то срочно делать!");
		}

		CustomersResponse response = new CustomersResponse();
		for (ru.menkin.ea.lec4.model.entities.Customer cust : dbCustomers)
		{
			Customer customer = Customer.map(cust);
			if (customer != null)
			{
				response.getCustomers().add(customer);
			}
		}
		return response;
	}
	
	//Tested
	// 5* /customers POST добавить клиента
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public @ResponseBody IdResponse createCustomer(@RequestBody CustomerRequest request)
	{
		ru.menkin.ea.lec4.model.entities.Customer dbCustomer = request.getCustomer().create();

		dbCustomer = _customerService.create(dbCustomer);

		IdResponse response = new IdResponse();
		response.setId(dbCustomer.getId());

		return response;
	}
	
	//Tested
	//6* /customers/{customerId} информация по клиенту GET
	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
	public @ResponseBody CustomerResponse getCustomer(
			@PathVariable("customerId") Integer id) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Customer dbCustomer = findCustomer(id);
		
		CustomerResponse response = new CustomerResponse();
		Customer customer = Customer.map(dbCustomer);
		
		if(customer != null)
		{
			response.setCustomer(customer);
		}
		
		return response;
	}
	
	//Tested
	//6* /customers/{customerId}  PUT обновить данные по клиенту
	@RequestMapping(value = "customers/{customerId}", method = RequestMethod.PUT)
	public @ResponseBody Response updateCustomer(
			@PathVariable("customerId") Integer id,
			@RequestBody CustomerRequest request) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Customer dbCustomer = findCustomer(id);
		
		request.getCustomer().update(dbCustomer);
		_customerService.update(dbCustomer);
		
		return new Response();
	}
	
	//Tested
	//6* /customers/{customerId}  DELETE удалить клиента
	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
	public @ResponseBody Response deleteCustomer(
			@PathVariable("customerId") Integer id) throws Exception
	{
		findCustomer(id);
		_customerService.delete(id);
		
		return new Response();
	}
	
	//Tested
	//8* /customer/{customersId}/orders/{orderId}  GET инфо по конкретному заказу  PUT - нельзя, DELETE - есть
	@RequestMapping(value = "/customers/{customerId}/orders/{orderId}", method = RequestMethod.GET)
	public @ResponseBody OrderResponse getOrder(
			@PathVariable("customerId") Integer id,
			@PathVariable("orderId") Integer orderId) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Order dbOrder = findOrder(id, orderId);
		
		OrderResponse response = new OrderResponse();
		Order order = Order.map(dbOrder);
		if(order != null)
		{
			response.setOrder(order);
		}
		return response;
	}
	
	//Tested
	//9* GET
	@RequestMapping(value = "/customers/{customerId}/orders/{orderId}/items", method = RequestMethod.GET)
	public @ResponseBody OrderItemsResponse getOrderItems(
			@PathVariable("customerId") Integer id,
			@PathVariable("orderId") Integer orderId) throws Exception
	{
		//проверяем есть ли такой клиент и заказ
		findOrder(id, orderId);

		List<ru.menkin.ea.lec4.model.entities.OrderItem> dbOrderItems = _orderItemService.findAll();
		if (dbOrderItems.isEmpty())
		{
			throw new Exception("Нет ни одного заказа!");
		}

		OrderItemsResponse response = new OrderItemsResponse();
		for(ru.menkin.ea.lec4.model.entities.OrderItem ordIt : dbOrderItems)
		{
			OrderItem orderItem = OrderItem.map(ordIt);
			if(orderItem != null)
			{
				response.getOrderItems().add(orderItem);
			}
		}
		return response;
	}
	
	//	{
	//	    "orderItem": { "quantity": "potato" }, 
	//		"product": { "id" : 19 },
	//		"warehouse": { "id" : 7 } 
	//	}
	//Tested
	//9* POST
	@RequestMapping(value = "/customers/{customerId}/orders/{orderId}/items", method = RequestMethod.POST)
	public @ResponseBody IdResponse createOrderItem(
			@PathVariable("customerId") Integer id,
			@PathVariable("orderId") Integer orderId,
			@RequestBody SpecificOrderItemRequest request) throws Exception
	{
		//проверяем есть ли такой клиент и заказ
		ru.menkin.ea.lec4.model.entities.Order dbOrder = findOrder(id, orderId);
			
		Integer productId = request.getProduct().getId();
		Integer warehouseId = request.getWarehouse().getId();
		
		ru.menkin.ea.lec4.model.entities.Product dbProduct = _productService.findById(productId);
		ru.menkin.ea.lec4.model.entities.Warehouse dbWarehouse = _warehouseService.findById(warehouseId);
		
		if(dbProduct == null || dbWarehouse == null)
		{
			log.error("Не могу найти склад или продукт");
			throw new Exception("Не могу найти склад или продукт");
		}
		
		ru.menkin.ea.lec4.model.entities.OrderItem dbOrderItem;
		
		dbOrderItem = request.getOrderItem().create();
		
		dbOrderItem.setOrder(dbOrder);
		dbOrderItem.setProduct(dbProduct);
		dbOrderItem.setWarehouse(dbWarehouse);
		
		dbOrderItem = _orderItemService.create(dbOrderItem);

		IdResponse response = new IdResponse();
		response.setId(dbOrderItem.getId());
		
		return response;
	}
	
	//Tested
	//10* GET
	@RequestMapping(value = "/customers/{customerId}/orders/{orderId}/items/{itemId}", method = RequestMethod.GET)
	public @ResponseBody OrderItemResponse getOrderItem(
			@PathVariable("customerId") Integer id,
			@PathVariable("orderId") Integer orderId,
			@PathVariable("itemId") Integer orderItemId) throws Exception
	{
		//проверяем есть ли такой клиент и заказ
		findOrder(id, orderId);
		
		ru.menkin.ea.lec4.model.entities.OrderItem dbOrderItem = findOrderItem(orderItemId);
		
		OrderItemResponse response = new OrderItemResponse();
		OrderItem orderItem = OrderItem.map(dbOrderItem);
		if(orderItem != null)
		{
			response.setOrderItem(orderItem);
		}
		return response;
	}
	
	//Tested
	//10* PUT
	@RequestMapping(value = "/customers/{customerId}/orders/{orderId}/items/{itemId}", method = RequestMethod.PUT)
	public @ResponseBody Response updateOrderItem(
			@PathVariable("customerId") Integer id,
			@PathVariable("orderId") Integer orderId,
			@PathVariable("itemId") Integer orderItemId,
			@RequestBody OrderItemRequest request) throws Exception
	{
		findOrder(id, orderId);
		ru.menkin.ea.lec4.model.entities.OrderItem dbOrderItem = findOrderItem(orderItemId);
		
		request.getOrderItem().update(dbOrderItem);
		_orderItemService.update(dbOrderItem);
		
		return new Response();
	}
	
	//tested
	//10* DELETE
	@RequestMapping(value = "/customers/{customerId}/orders/{orderId}/items/{itemId}", method = RequestMethod.DELETE)
	public @ResponseBody Response deleteOrderItem(
			@PathVariable("customerId") Integer id,
			@PathVariable("orderId") Integer orderId,
			@PathVariable("itemId") Integer orderItemId) throws Exception
	{
		findOrder(id, orderId);
		findOrderItem(orderItemId);
		
		_orderItemService.delete(orderItemId);
		return new Response();
	}
	
	// Вспомогательные функции
	//Поиск склада
	private ru.menkin.ea.lec4.model.entities.Warehouse findWarehouse(Integer id) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Warehouse warehouse = _warehouseService.findById(id);
		if (warehouse == null)
		{
			throw new Exception(String.format("Склад с идентификатором %d не найден", id));
		}
		return warehouse;
	}
	
	//Поиск категории
	private ru.menkin.ea.lec4.model.entities.Category findCategory(Integer id) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Category category = _categoryService.findById(id);
		if (category == null)
		{
			throw new Exception(String.format("Категории с идентификатором %d не существует", id));
		}
		return category;
	}
	
	//Поиск ордер итем
	private ru.menkin.ea.lec4.model.entities.OrderItem findOrderItem(Integer id) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.OrderItem orderItem = _orderItemService.findById(id);
		if(orderItem == null)
		{
			throw new Exception(String.format("Итем с идентификатором %d не найден", id));
		}
		return orderItem;
	}
	//Поиск клиента
	private ru.menkin.ea.lec4.model.entities.Customer findCustomer(Integer id) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Customer customer = _customerService.findById(id);
		if (customer == null)
		{
			throw new Exception(String.format("Клиент с идентификатором %d не найден", id));
		}
		return customer;
	}
	
	//Поиск заказа у клиента
	private ru.menkin.ea.lec4.model.entities.Order findOrder(Integer id, Integer orderId) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Customer dbCustomer = findCustomer(id);
		ru.menkin.ea.lec4.model.entities.Order dbOrder = null;

		for (ru.menkin.ea.lec4.model.entities.Order iDbOrder : dbCustomer.getOrders())
		{
			if (iDbOrder.getId().equals(orderId))
			{
				dbOrder = iDbOrder;
				break;
			}
		}
		if (dbOrder == null)
		{
			log.error(String.format("Заказ %d у клиента %d не найден", orderId, id));
			throw new Exception(String.format("Заказ %d у клиента %d не найден", orderId, id));
		}
		return dbOrder;
	}
	
	// Поиск продукта на складе
	private ru.menkin.ea.lec4.model.entities.Product findProduct(Integer id, Integer prodId) throws Exception
	{
		ru.menkin.ea.lec4.model.entities.Warehouse dbWarehouse = findWarehouse(id);
		
		ru.menkin.ea.lec4.model.entities.Product dbProduct = null;

		for (ru.menkin.ea.lec4.model.entities.Product iDbProduct : dbWarehouse.getProducts())
		{
			if (iDbProduct.getId().equals(prodId))
			{
				dbProduct = iDbProduct;
				break;
			}
		}

		if (dbProduct == null)
		{
			throw new Exception(String.format("Товар %d на складе %d не найден", prodId, id));
		}
		return dbProduct;
	}
}