package com.pro.assessment.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pro.assessment.bean.Customer;
import com.pro.assessment.bean.NickName;
import com.pro.assessment.bean.Product;
import com.pro.assessment.bean.Results;
import com.pro.assessment.database.DatabaseClass;

public class ServiceDAO {
	
	
	DatabaseClass dbc = new DatabaseClass();

	public ServiceDAO() {
		// TODO Auto-generated constructor stub
	}
	


public List getCust() {
		Connection c = dbc.getConnection();
		   Statement stmt = null;
		   
		   List list = new ArrayList();
		   
		   try {
			    
			      c.setAutoCommit(false);

			      stmt = c.createStatement();
			      
			      String sql = "SELECT Id,CompanyName FROM Customer order by CompanyName";
			    
			      ResultSet rs = stmt.executeQuery(sql);
			      
			  
			      while ( rs.next() ) {
			         String custId = rs.getString("Id");
			         String  custName = rs.getString("CompanyName");
			         
			         Customer cust = new Customer(custId,custName);
			         list.add(cust);
			      }
			     
			      rs.close();
			      stmt.close();
			     
			      c.close();
			   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() )
			      ;
			      System.exit(0);
			   }
			 
	
		return list;
		   
	}



	public List getProduct() {
		Connection c = dbc.getConnection();
		   
		   Statement stmt1 = null;
		   List list = new ArrayList();
		   
		   try {
			    
			      c.setAutoCommit(false);

			      stmt1 = c.createStatement();
			      
			      String sql1 = "Select Id,ProductName from Product order by ProductName";
			     
			      ResultSet rs1 = stmt1.executeQuery(sql1);
			  
			 
			      while ( rs1.next() ) {
				         String ProdName = rs1.getString("ProductName");
				         String ProdId = rs1.getString("Id");
				        
				        Product prod = new Product(ProdName,ProdId);
				         list.add(prod);
				      }
			   
			      rs1.close();
			      stmt1.close();
			      c.close();
			   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() )
			      ;
			      System.exit(0);
			   }
			 
	
		return list;
		   
	}
	


	public List getProductCust(String CustId) {
		Connection c = dbc.getConnection();
		   
		   Statement stmt1 = null;
		   List list = new ArrayList();
		   
		   try {
			    
			      c.setAutoCommit(false);

			      stmt1 = c.createStatement();
			      
			      String sql1 = "select distinct product.productname,Product.Id from OrderDetail,product where orderid in (select id from \"Order\" where Customerid = '"+CustId+"') and product.id = OrderDetail.ProductId order by product.productname";
			     System.out.println(sql1);
			      ResultSet rs1 = stmt1.executeQuery(sql1);
			  
			 
			      while ( rs1.next() ) {
				         String ProdName = rs1.getString("ProductName");
				         String ProdId = rs1.getString("Id");
				        
				        Product prod = new Product(ProdName,ProdId);
				         list.add(prod);
				      }
			   
			      rs1.close();
			      stmt1.close();
			      c.close();
			   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() )
			      ;
			      System.exit(0);
			   }
			 
	
		return list;
		   
	}



	public List getTotAvg(int[] prods, String CustId) {
		Connection c = dbc.getConnection();
		   
		   Statement stmt1 = null;
		   Statement stmt = null;
		 

		   List lis = new ArrayList();
		   String prodArr = prods[0] + "";
		   
		   for(int i = 1;i<prods.length;i++){
			   prodArr = prodArr + "," + prods[i];
		   }
		   System.out.println(prodArr);
		   try {
			    
			      c.setAutoCommit(false);

			      stmt1 = c.createStatement();
			      stmt = c.createStatement();
			      
			      System.out.println(prods);
			      System.out.println(CustId);
			      String sql1 = "select sum((unitprice-discount)*quantity) as total from OrderDetail where orderid in (select id from \"Order\" where Customerid = '"+CustId+"') and productid in ("+prodArr+")";
			     
			      
			     String sql =  "select sum((unitprice-discount)*quantity)/(select count(orderID) from OrderDetail where orderid in (select id from \"Order\" where Customerid = '"+CustId+"') and productid in ("+prodArr+")" +
			      ") as average from OrderDetail where orderid in (select id from \"Order\" where Customerid = '"+CustId+"') and productid in ("+prodArr+")";
			     	
			     System.out.println(sql);
			     System.out.println(sql1);
			      ResultSet rs1 = stmt1.executeQuery(sql1);
			      ResultSet rs = stmt.executeQuery(sql);
			  
			 int total = 0;
			      while ( rs1.next() ) {
				         total = rs1.getInt("total");
				      }
			      int avg = 0;

			      while ( rs.next() ) {
				         avg = rs.getInt("average");
				      }
			      
			    Results ress = new Results(total,avg);
			    lis.add(ress);
			      rs1.close();
			      stmt1.close();
			      rs.close();
			      stmt.close();
			      c.close();
			   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() )
			      ;
			      System.exit(0);
			   }
			 

		return lis;
		   
	}



	public List getNickName(String custId) {
		Connection c = dbc.getConnection();
		   
		   Statement stmt1 = null;
		   List list = new ArrayList();
		   
		   try {
			    
			      c.setAutoCommit(false);

			      stmt1 = c.createStatement();
			      
			      String sql1 = "Select nickname from custnicknames where custId = '"+custId+"' order by nickname" ;
			     
			      ResultSet rs1 = stmt1.executeQuery(sql1);
			      
			      
			  
			 
			      while ( rs1.next() ) {
				         String nickname = rs1.getString("nickname");
				      
				        
				        NickName nick = new NickName(nickname);
				         list.add(nick);
				      }
			   
			      rs1.close();
			      stmt1.close();
			      c.close();
			   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() )
			      ;
			      System.exit(0);
			   }
			 
	
		return list;
	}



	public List addNickName(String custId,String nickname) {
		Connection c = dbc.getConnection();
		   System.out.println("nicknames:"+nickname);
		   Statement stmt1 = null;
		   List list = new ArrayList();
		   
		   try {
			    
			      //c.setAutoCommit(false);

			      stmt1 = c.createStatement();
			     
			      String sql1 = "INSERT INTO custnicknames(custid,nickname) values('"+custId+"','"+nickname+"')" ;
			     System.out.println(sql1);
			      int rs1 = stmt1.executeUpdate(sql1);
			      System.out.println("this is rs: "+rs1);
			      if(rs1==1)list = getNickName(custId);
			      stmt1.close();
			      c.close();
			   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() )
			      ;
			      System.exit(0);
			   }
			 
	
		return list;
	}

}