package com.example.gwtmvpdemo.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.example.gwtmvpdemo.client.StorageService;
import com.example.gwtmvpdemo.client.event.AddLaptopEvent;
import com.example.gwtmvpdemo.client.event.EditLaptopEvent;
import com.example.gwtmvpdemo.shared.model.Laptop;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.smartgwt.client.widgets.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LaptopsPresenter implements Presenter {

	final HandlerManager eventBus;
	final StorageService storage;
	private final Display display;
	List<Laptop> data = new ArrayList<Laptop>();
	List<Laptop> storageDate = new ArrayList<Laptop>();
	List<Integer> selectedId = new ArrayList<Integer>();
	final DialogBox dialogBox = new DialogBox();
	final Button close = new Button("Close");

	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getDeleteButton();

		void setData(List<Laptop> data);

		Widget asWidget();

		List<Integer> getSelectedRows();

		HasClickHandlers getEditButton();

	}

	public LaptopsPresenter(StorageService storage, HandlerManager eventBus,
			Display view) {
		this.eventBus = eventBus;
		this.storage = storage;
		this.display = view;
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		fetchLaptops();
	}

	private void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddLaptopEvent());
			}
		});
		display.getDeleteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteSelectedRows();
			}
		});
		display.getEditButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (display.getSelectedRows().size() == 1) {
					int id = display.getSelectedRows().get(0);
					eventBus.fireEvent(new EditLaptopEvent(id));
				} else {
					showWindowMessage("edit");
				}

			}
		});
	}

	private void fetchLaptops() {
		storageDate = storage.getAllLaptops();

		for (int i = 0; i < storageDate.size(); i++) {
			data.add(storageDate.get(i));
		}
		display.setData(data);
	}

	private void deleteSelectedRows() {

		selectedId = display.getSelectedRows();
		
			storageDate = storage.getAllLaptops();

			for (int i = 0; i < storageDate.size(); i++) {
				for (int j = 0; j < selectedId.size(); j++) {
					if (storageDate.get(i).getId() == selectedId.get(j)) {
						storageDate.remove(i);
					}
				}

			}

			display.setData(storageDate);
		
	}

	private void showWindowMessage(String mode) {

		close.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();

			}
		});
		VerticalPanel dialogContents = new VerticalPanel();

		if (mode == "edit") {

			dialogBox.setText("You must salect one row!");

		} 

		dialogBox.setWidget(dialogContents);
		dialogContents.add(close);
		dialogBox.center();
		dialogBox.show();
	}

}
