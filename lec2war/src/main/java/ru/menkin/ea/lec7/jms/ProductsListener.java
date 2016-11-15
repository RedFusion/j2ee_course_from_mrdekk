package ru.menkin.ea.lec7.jms;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.menkin.ea.lec4.model.services.ICategoryService;
import ru.menkin.ea.lec4.model.services.IProductService;
import ru.menkin.ea.lec4.model.services.IWarehouseService;
import ru.menkin.ea.lec7.model.entities.Category;
import ru.menkin.ea.lec7.model.entities.Product;
import ru.menkin.ea.lec7.model.entities.Warehouse;
import ru.menkin.ea.lec7.model.responses.ProductsResponse;

public class ProductsListener implements MessageListener
{
	private static final Logger log = LoggerFactory.getLogger(ProductsListener.class);

	private IProductService _productService;
	private ICategoryService _categoryService;
	private IWarehouseService _warehouseService;

	// @formatter:off
	public IProductService getProductService() { return _productService; }
	@Required
	public void setProductService(IProductService productService) { _productService = productService; }
	
	public ICategoryService getCategoryService() { return _categoryService; }
	@Required
	public void setCategoryService(ICategoryService categoryService) { _categoryService = categoryService; }
	
	public IWarehouseService getWarehouseService() { return _warehouseService; }
	@Required
	public void setWarehouseService(IWarehouseService warehouseService) { _warehouseService = warehouseService; }
	// @formatter:on

	@Override
	public void onMessage(Message message)
	{
		try
		{
			String json = null;
			if (message instanceof TextMessage)
			{
				json = ((TextMessage) message).getText();
			}
			else if (message instanceof BytesMessage)
			{
				BytesMessage msg = (BytesMessage) message;
				// Только для демонстрации, в реальной жизни так делать не стоит
				byte[] data = new byte[(int) msg.getBodyLength()];
				int read = msg.readBytes(data);
				json = new String(data, 0, read, "UTF-8");
				// json = new ObjectMapper().writeValueAsString(msg);
			}
			if (json == null)
			{
				log.error("ProductsListener exception json == null");
				throw new Exception("Wrong message!");
			}

			ObjectMapper jsonMapper = new ObjectMapper();
			ProductsResponse response = jsonMapper.readValue(json, ProductsResponse.class);

			Warehouse warehouse = new Warehouse();
			warehouse.setName("ActiveMQ");
			warehouse.setCapacity(response.getProducts().size() * 10);

			ru.menkin.ea.lec4.model.entities.Warehouse dbWarehouse = warehouse.create();
			dbWarehouse = _warehouseService.create(dbWarehouse);

			for (Product product : response.getProducts())
			{
				ru.menkin.ea.lec4.model.entities.Product dbProduct = product.create();

				Category category = new Category();
				if (product.getCategories().isEmpty())
				{
					category.setName("ActiveMQ");
				}
				else
				{
					category.setName(product.getCategories().get(0));
				}
				ru.menkin.ea.lec4.model.entities.Category dbCategory = category.create();
				dbCategory = _categoryService.create(dbCategory);

				dbProduct.setCategory(dbCategory);
				dbProduct.setWarehouse(dbWarehouse);
				dbProduct = _productService.create(dbProduct);
				
				log.info(dbProduct.toString());
			}
		}
		catch (Exception e)
		{
			log.error("ProductsListener exception" + e.getLocalizedMessage());
		}
	}
}