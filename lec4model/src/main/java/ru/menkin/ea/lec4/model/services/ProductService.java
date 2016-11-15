package ru.menkin.ea.lec4.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.menkin.ea.lec4.model.entities.Product;
import ru.menkin.ea.lec4.model.repositories.ProductRepository;

public class ProductService implements IProductService
{

	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Product create(Product product)
	{
		return productRepository.save(product);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Product delete(int id) throws Exception
	{
		Product deletedProduct = productRepository.findOne(id);

		if (deletedProduct == null)
		{
			throw new Exception("Product not found");
		}

		productRepository.delete(deletedProduct);

		return deletedProduct;
	}

	@Override
	public List<Product> findAll()
	{
		return productRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Product update(Product product) throws Exception
	{
		Product updatedProduct = productRepository.findOne(product.getId());

		if (updatedProduct == null)
		{
			throw new Exception("Product not found");
		}

		updatedProduct.setName(product.getName());
		updatedProduct.setCategory(product.getCategory());
		updatedProduct.setDescription(product.getDescription());
		updatedProduct.setDiscount(product.getDiscount());
		updatedProduct.setPrice(product.getPrice());
		updatedProduct.setOrderItems(product.getOrderItems());
		updatedProduct.setQuantity(product.getQuantity());
		updatedProduct.setWarehouse(product.getWarehouse());

		productRepository.save(updatedProduct);

		return updatedProduct;
	}

	@Override
	public Product findById(int id)
	{
		return productRepository.findOne(id);
	}

	// Lecture 5
	@Override
	public Product findByName(String name)
	{
		List<Product> products = productRepository.getProductByName(name);
		if (products == null || products.isEmpty())
		{
			return null;
		}
		return products.get(0);
	}
}
