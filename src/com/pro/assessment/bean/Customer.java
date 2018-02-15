package com.pro.assessment.bean;

public class Customer {
	
	String CustId;
	String CustName;
	
	public Customer(String custId, String custName) {
		super();
		CustId = custId;
		CustName = custName;
	
	}
	public String getCustId() {
		return CustId;
	}
	public void setCustId(String custId) {
		CustId = custId;
	}
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}

	
	

}
