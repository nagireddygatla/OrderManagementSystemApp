package com.pro.assessment.bean;

public class Product {

	String ProductName;
	String ProductId;

	public Product(String productName, String productId) {
		super();
		ProductName = productName;
		ProductId = productId;
	}



	public String getProductId() {
		return ProductId;
	}



	public void setProductId(String productId) {
		ProductId = productId;
	}



	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}
	
	
}
