package com.cookpick.dto;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	
	@Pattern(regexp = "[A-Z]{3}[0-9]+", message = "Product code is not as per standerd.")
	private String productCode;
	
	private String productName;
	
	public Product() {
		super();
	}

	private String productVendor;
	
	private Double productPrice;
	
	private int productInStock;
	
	
	
	public Product(String productCode, String productName, String productVendor, Double productPrice,
			int productInStock) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productVendor = productVendor;
		this.productPrice = productPrice;
		this.productInStock = productInStock;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductInStock() {
		return productInStock;
	}

	public void setProductInStock(int productInStock) {
		this.productInStock = productInStock;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", productVendor="
				+ productVendor + ", productPrice=" + productPrice + ", productInStock=" + productInStock + "]";
	}

	

}
