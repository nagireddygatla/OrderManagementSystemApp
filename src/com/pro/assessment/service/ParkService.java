package com.pro.assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.pro.assessment.dao.ServiceDAO;

@Service
@ComponentScan("com.pro.assessment")
public class ParkService {
	
	ServiceDAO sd = new ServiceDAO();

	
	public List getCust() {
		// TODO Auto-generated method stub
		return sd.getCust();
	}

	public List getProductCust(String CustId) {
		// TODO Auto-generated method stub
		return sd.getProductCust(CustId);
	}
	
	public List getProduct() {
		// TODO Auto-generated method stub
		return sd.getProduct();
	}



	public List getTotAvg(int[] prods, String custId) {
		// TODO Auto-generated method stub
		return sd.getTotAvg(prods,custId);
	}

	public List getNickNames(String custId) {
		
		return sd.getNickName(custId);
	}

	public List addNickName(String custId,String nickname) {
		return sd.addNickName(custId,nickname);
	}
	



}