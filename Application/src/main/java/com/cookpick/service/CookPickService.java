package com.cookpick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cookpick.dto.Product;
import com.cookpick.exception.NoSuchProductException;
import com.cookpick.repository.CookPickRepository;

@Service
@PropertySource("classpath:ValidationMessages.properties")
public class CookPickService {
	
	@Autowired
	private CookPickRepository cpRepo;
	
	@Autowired
	Environment env;
	
	public String addProduct(Product product) {
		String result = cpRepo.addProduct(product);
		if (result!= null)
			return product.getProductName();
		return null;
	}
	
	public Product getProduct(String productCode) throws NoSuchProductException {
		Product product = cpRepo.getProduct(productCode);
		return product;
	}
	
	public String removeProduct(String productCode) throws NoSuchProductException {
		String result =  cpRepo.removeProduct(productCode);
		return result;
	}
	
	public String updateProduct(String productCode, Product product) {
		cpRepo.updateProduct(productCode, product);
			
		return  env.getProperty("product.update.success");
	}
}
