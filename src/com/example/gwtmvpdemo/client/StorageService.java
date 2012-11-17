package com.example.gwtmvpdemo.client;

import java.util.ArrayList;
import java.util.List;

import com.example.gwtmvpdemo.shared.model.Laptop;

public class StorageService {
 
  private List<Laptop> db = new ArrayList<Laptop>();
  
  public void addLaptop(Laptop laptop){
	  Laptop l = new Laptop();
	  l.setId(laptop.getId());
	  l.setModel(laptop.getModel());
	  l.setProducent(laptop.getProducent());
	  l.setDatebox(laptop.getDatebox());
	  db.add(l);
  }
  
  public List<Laptop> getAllLaptops(){
	  return db;
  }
  
  public Laptop getLaptopById(int id){
	  Laptop laptop = new Laptop();
	  for(int i=0; i < getAllLaptops().size(); i++) {
		  if(getAllLaptops().get(i).getId() == id){
			  laptop = getAllLaptops().get(i);
		  }
	  }
	  return laptop;  
  }
  
  public void updateLaptop(Laptop laptop){
	for(int i=0;i<getAllLaptops().size();i++){
		if(getAllLaptops().get(i).getId() == laptop.getId()){
			getAllLaptops().get(i).setModel(laptop.getModel());
			getAllLaptops().get(i).setProducent(laptop.getProducent());
			getAllLaptops().get(i).setDatebox(laptop.getDatebox());
		}
	}
	  
  }
}
