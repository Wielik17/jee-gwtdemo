package com.example.gwtmvpdemo.shared.model;

import java.util.Date;

public class Laptop{
	
	
	private int id;
	private String model;
	private String producent;
	private Date date;
	
	public Laptop() {}
	
	public Laptop(int i,String model,String producent,Date date) {
		this.id = i;
		this.model = model;
		this.producent = producent;
		this.date = date;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getProducent() {
		return producent;
	}
	public void setProducent(String producent) {
		this.producent = producent;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Date getDatebox() {
		return date;
	}

	public void setDatebox(Date date) {
		this.date = date;
	}
	
}
