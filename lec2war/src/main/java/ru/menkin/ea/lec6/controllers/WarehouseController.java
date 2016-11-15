package ru.menkin.ea.lec6.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ru.menkin.ea.lec4.model.services.IOrderService;
import ru.menkin.ea.lec4.model.services.IProductService;
import ru.menkin.ea.lec4.model.services.IWarehouseService;
import ru.menkin.ea.lec5.web.model.entities.Warehouse;
import ru.menkin.ea.lec5.web.model.responses.ErrorResponse;
import ru.menkin.ea.lec5.web.model.responses.Response;
import ru.menkin.ea.lec5.web.model.responses.WarehouseResponse;
import ru.menkin.ea.lec6.requests.IdRequest;

@Controller
public class WarehouseController
{
	@Autowired
	@Qualifier("warehouseService")
	private IWarehouseService _warehouseService;
	
	@Autowired
	@Qualifier("productService")
	private IProductService _productService;
	
	@Autowired
	@Qualifier("orderService")
	private IOrderService _orderService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public @ResponseBody Response getInfo(@RequestBody IdRequest request)
	{
		try
		{
			WarehouseResponse response = new WarehouseResponse();
			Warehouse warehouse = Warehouse.map(_warehouseService.findById(request.getId()));
			response.setWarehouse(warehouse);
			
			return response;
			
		} catch (Exception exc)
		{
			ErrorResponse response = new ErrorResponse();
			response.setStatus("fail");
			response.setMessage(exc.getMessage());

			return response;
		}
	}
	
	@RequestMapping(value = "/warehouse", method = RequestMethod.GET)
	public ModelAndView getWarehouseProduct(@RequestParam("id") Integer id)
	{
		ModelAndView model = new ModelAndView("warehouse");
		
		List<ru.menkin.ea.lec4.model.entities.Product> products = new ArrayList<>();
		for(ru.menkin.ea.lec4.model.entities.Product prod : _productService.findAll())
		{
			if(prod.getWarehouse().getId().equals(id))
			{
				products.add(prod);
			}
		}
		model.addObject("warehouse", _warehouseService.findById(id));
		model.addObject("products", products);
		
		return model;
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam("id") Integer id)
	{
		ModelAndView model = new ModelAndView("product");
		model.addObject("product", _productService.findById(id));
		
		return model;
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView getOrder(@RequestParam("id") Integer id)
	{
		ModelAndView model = new ModelAndView("order");
		model.addObject("order", _orderService.findById(id));
		
		return model;
	}
}