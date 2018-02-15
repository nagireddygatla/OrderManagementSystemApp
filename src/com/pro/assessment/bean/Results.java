package com.pro.assessment.bean;

public class Results {

	int total;
	int avg;
	public Results(int total, int avg) {
		super();
		this.total = total;
		this.avg = avg;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getAvg() {
		return avg;
	}
	public void setAvg(int avg) {
		this.avg = avg;
	}
	
	
}
