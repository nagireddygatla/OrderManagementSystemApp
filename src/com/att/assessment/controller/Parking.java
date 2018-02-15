package com.att.assessment.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.assessment.service.ParkService;

@RestController
@SpringBootApplication
@ComponentScan("com.pro.assessment")
public class Parking{
	@Autowired	
	ParkService ps;
	


	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(method=RequestMethod.POST, value="/getCust",headers="Accept=application/json")
	public List getCust(){
		return ps.getCust();
	}
	
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(method=RequestMethod.POST, value="/getProd",headers="Accept=application/json")
	public List getProduct(){
		return ps.getProduct();
	}
	
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(method=RequestMethod.POST, value="/getProdCust",headers="Accept=application/json")
	public List getProductCust(@RequestParam("CustId") String CustId){
		return ps.getProductCust(CustId);
	}
	
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(method=RequestMethod.POST, value="/getNickName",headers="Accept=application/json")
	public List getNickNames(@RequestParam("CustId") String CustId){
		return ps.getNickNames (CustId);
	}
	
	
	
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(method=RequestMethod.POST, value="/addNickName",headers="Accept=application/json")
	public List addNickName(@RequestParam("CustId") String CustId,@RequestParam("nickname") String nickname){
		return ps.addNickName (CustId,nickname);
	}
	
	

	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(method=RequestMethod.POST, value="/getTotAvg")
	public List getTotAvg(@RequestParam("prods[]") int [] prods,@RequestParam("custId") String custId){
		System.out.println("This is array"+Arrays.toString(prods));
		if(prods[0] == -10)return null;
		return ps.getTotAvg(prods,custId);
	}
	
	  public static void main(String[] args) throws Exception {
	        SpringApplication.run(Parking.class, args);
	    }
	  
/*	  @Configuration
	  @EnableWebMvc
	  public class WebConfig extends WebMvcConfigurerAdapter {

	  	@Override
	  	public void addCorsMappings(CorsRegistry registry) {
	  		registry.addMapping("/**");
	  	}
	  }*/
	
}
