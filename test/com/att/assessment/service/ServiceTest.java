package com.att.assessment.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.pro.assessment.bean.Results;
import com.pro.assessment.service.ParkService;

public class ServiceTest {
	
	private ParkService parkService = new ParkService();


	@Test
	public void ProdList() {
		
		List result = parkService.getProductCust("ALFKI");
		boolean sizetest = true;
		if(result.size() == 0)sizetest = false;
		assertEquals(sizetest,true);
		
	}
	
	@Test
	public void getTotAvg() {
		int [] prods = {1,2};
		String custID = "ALFKI";
		List<Results> result = parkService.getTotAvg(prods,custID);
		int tot=0,avg=0;
		for(Results res:result){
			tot = res.getTotal();
			avg = res.getAvg();
		}
		
		boolean test = false;
		if(tot == 102566 && avg == 470)test = true;
		
		assertEquals(test,true);
		
	}
	

	
	

}
