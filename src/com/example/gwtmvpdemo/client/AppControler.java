package com.example.gwtmvpdemo.client;

import com.example.gwtmvpdemo.client.event.*;
import com.example.gwtmvpdemo.client.presenter.*;
import com.example.gwtmvpdemo.client.view.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppControler implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final StorageService storage;
	private HasWidgets container;

	private final static String SHOW_LAPTOPS = "showLaptops";
	private final static String ADD_LAPTOP = "addLaptop";
	private final static String EDIT_LAPTOP = "editLaptop";

	public AppControler(StorageService storage, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.storage = storage;
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem(ADD_LAPTOP);
		} else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {

		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals(SHOW_LAPTOPS)) {
				presenter = new LaptopsPresenter(storage, eventBus,
						new LaptopsView());
			} else if (token.equals(ADD_LAPTOP)) {
				presenter = new AddLaptopPresenter(storage, eventBus,
						new AddLaptopView());
			} else if (token.equals(EDIT_LAPTOP)) {
				presenter = new EditLaptopPresenter(storage, eventBus,
						new EditLaptopView());
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(AddLaptopEvent.TYPE, new AddLaptopEventHandler() {
			@Override
			public void onAddLaptop(AddLaptopEvent event) {
				doAddLaptop();
			}
		});

		eventBus.addHandler(ShowLaptopsEvent.TYPE,
				new ShowLaptopsEventHandler() {
					@Override
					public void onShowLaptops(ShowLaptopsEvent event) {
						doShowLaptops();
					}
				});
		eventBus.addHandler(EditLaptopEvent.TYPE, new EditLaptopEventHandler() {
			@Override
			public void onEditLaptop(EditLaptopEvent event) {
				doEditLaptops(event.getId());
			}
		});
	}

	private void doAddLaptop() {
		History.newItem(ADD_LAPTOP);
	}

	private void doShowLaptops() {
		History.newItem(SHOW_LAPTOPS);
	}

	private void doEditLaptops(int id) {
		History.newItem(EDIT_LAPTOP);
		Presenter presenter = new EditLaptopPresenter(storage, eventBus, new EditLaptopView(), id);
	    presenter.go(container);
	}

}
