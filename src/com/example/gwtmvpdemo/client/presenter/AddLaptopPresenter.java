package com.example.gwtmvpdemo.client.presenter;

import java.util.List;

import com.example.gwtmvpdemo.client.StorageService;
import com.example.gwtmvpdemo.client.event.ShowLaptopsEvent;
import com.example.gwtmvpdemo.shared.model.Laptop;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class AddLaptopPresenter implements Presenter {

	final HandlerManager eventBus;
	final StorageService storage;
	private final Display display;
	private Laptop laptop;
	private List<Laptop> allLaptops;

	public interface Display {
		HasClickHandlers getShowLaptopsButton();

		HasClickHandlers getAddLaptopsButton();

		HasValue<String> getModelTextBox();

		HasValue<String> getProducentTextBox();
		
		DateBox getDateBox();

		Widget asWidget();

		boolean checkEmpty();

		boolean existMessage();

	}

	public AddLaptopPresenter(StorageService storage, HandlerManager eventBus,
			Display view) {
		this.eventBus = eventBus;
		this.storage = storage;
		this.display = view;
		this.laptop = new Laptop();
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
		display.getShowLaptopsButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ShowLaptopsEvent());
			}

		});

		display.getAddLaptopsButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (!checkExist() && !display.checkEmpty()) {
					doAddLaptopToList();
					eventBus.fireEvent(new ShowLaptopsEvent());
				}
			}
		});
	}

	private void doAddLaptopToList() {
		int id = 0;
		allLaptops = storage.getAllLaptops();
		for (int i = 0; i < allLaptops.size(); i++) {
			if (allLaptops.get(i).getId() > id) {
				id = allLaptops.get(i).getId();
			}
		}

		laptop.setId(id + 1);
		laptop.setModel(display.getModelTextBox().getValue());
		laptop.setProducent(display.getProducentTextBox().getValue());
		laptop.setDatebox(display.getDateBox().getValue());
		storage.addLaptop(laptop);
	}
	
	private boolean checkExist(){
		allLaptops = storage.getAllLaptops();
		for (int i = 0; i < allLaptops.size(); i++) {
			if(allLaptops.get(i).getModel().equals(display.getModelTextBox().getValue()) 
				&& allLaptops.get(i).getProducent().equals(display.getProducentTextBox().getValue())){
				if(display.existMessage()){
					return true;
				}
			}
		}
		return false;
	}
}
