package com.cookpick.repository;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.cookpick.dto.Product;
import com.cookpick.exception.NoSuchProductException;

@Repository
public class CookPickRepository {
	
	static HashMap<String ,Product> productDB = new HashMap<>();
	
	@PostConstruct
	public void initializer() {
		Product p1 = new Product("CBK1", "Dessert Cook Book", "FY18", 300.00, 18);
		Product p2 = new Product("PAN1", "Essential Pan", "Niplon", 950.00, 60);
		Product p3 = new Product("PAN2", "Non-Stick Pan", "Teflon", 1050.00, 37);
		productDB.put(p1.getProductCode(), p1);
		productDB.put(p2.getProductCode(), p2);
		productDB.put(p3.getProductCode(), p3);
	}
	
	public String addProduct(Product product) {
		productDB.put(product.getProductCode(), product);
		return product.getProductName();
	}
	
	public Product getProduct(String productCode) throws NoSuchProductException{
		Product product =productDB.get(productCode);
		if(product != null)
			return product;
		else {
			 throw new NoSuchProductException(" No Product available with code: "+productCode);
		}
	}
	
	public String removeProduct(String productCode) throws NoSuchProductException {
		Product product =productDB.get(productCode);
		if(product != null) {
			productDB.remove(productCode);
			return "Success";
		}
			
		else {
			 throw new NoSuchProductException(" No Product available with code: "+productCode);
		}
		
	}
	
	public boolean updateProduct(String productCode, Product product) {
		Product oldProduct =productDB.get(productCode);
		if(oldProduct != null) {
			productDB.replace(productCode, product);
		}
		return true;		
	}

}
