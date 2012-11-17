package com.example.gwtmvpdemo.client.presenter;


import java.util.ArrayList;
import java.util.List;

import com.example.gwtmvpdemo.client.presenter.LaptopsPresenter;
import com.example.gwtmvpdemo.client.view.AddLaptopView;
import com.example.gwtmvpdemo.client.view.EditLaptopView;
import com.example.gwtmvpdemo.client.view.LaptopsView;
import com.example.gwtmvpdemo.client.StorageService;
import com.example.gwtmvpdemo.client.event.ShowLaptopsEvent;
import com.example.gwtmvpdemo.shared.model.Laptop;
import com.google.gwt.dev.asm.commons.Method;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class EditLaptopPresenter implements Presenter{

	final HandlerManager eventBus;
	final StorageService storage;
	private final Display display;
	final Laptop laptop;
	final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
	
	public interface Display {
		

		Widget asWidget();

		HasClickHandlers getUpdateButton();

		void setDate(Laptop laptop);

		HasValue<String> getModelTextBox();

		HasValue<String> getProducentTextBox();

		DateBox getDateBox();
	}

	public EditLaptopPresenter(StorageService storage, HandlerManager eventBus,
			Display view) {
		this.eventBus = eventBus;
		this.storage = storage;
		this.display = view;
		this.laptop = new Laptop();
		bind();
	}


	public EditLaptopPresenter(StorageService storage,
			HandlerManager eventBus, Display view, int id) {
		this.eventBus = eventBus;
		this.storage = storage;
		this.display = view;
		this.laptop = storage.getLaptopById(id);
		bind();
	}


	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchLaptop();
	}

	private void bind() {
		display.getUpdateButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				doUpdateLaptop();
				eventBus.fireEvent(new ShowLaptopsEvent());
				
			}
		});
	}

	public void fetchLaptop(){
		display.setDate(laptop);
	}
	public void doUpdateLaptop() {
		laptop.setModel(display.getModelTextBox().getValue());
		laptop.setProducent(display.getProducentTextBox().getValue());
		laptop.setDatebox(display.getDateBox().getValue());
		storage.updateLaptop(laptop);
	}

}
