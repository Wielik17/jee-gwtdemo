package com.example.gwtmvpdemo.client.view;

import com.example.gwtmvpdemo.client.presenter.EditLaptopPresenter.Display;
import com.example.gwtmvpdemo.shared.model.Laptop;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.datepicker.client.DateBox;

public class EditLaptopView extends Composite implements Display {

	private final Button updateButton;
	private final TextBox modelTextBox;
	private final TextBox producentTextBox;
	private final DateBox dateBox;
	private final Label modelLabel;
	private final Label producentLabel;
	private final Label dateLabel;
	
	public EditLaptopView() {
		modelTextBox = new TextBox();
		producentTextBox = new TextBox();
		modelLabel = new Label("model:");
		producentLabel = new Label("producent:");
		dateLabel = new Label("date:");
		VerticalPanel mainPanel = new VerticalPanel();
		initWidget(mainPanel);
		updateButton = new Button("Update Laptop");
		
		// Create a table to layout the form options
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a title to the form
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		@SuppressWarnings("deprecation")
		DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
	    dateBox = new DateBox();
	    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
	    
		// Add some standard form options
		layout.setWidget(1, 0, modelLabel);
		layout.setWidget(1, 1, modelTextBox);
		layout.setWidget(2, 0, producentLabel);
		layout.setWidget(2, 1, producentTextBox);
		layout.setWidget(3, 0, dateLabel);
		layout.setWidget(3, 1, dateBox);
		layout.setWidget(4, 0, updateButton);

		// Wrap the content in a DecoratorPanel
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(layout);
		mainPanel.add(decPanel);
	}

	@Override
	public HasClickHandlers getUpdateButton() {
		return updateButton;
	}
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override 
	public DateBox getDateBox(){
		return dateBox;
	}
	
	@Override
	public void setDate(Laptop laptop) {
		modelTextBox.setText(laptop.getModel());
		producentTextBox.setText(laptop.getProducent());
		dateBox.setValue(laptop.getDatebox());
	}

	@Override
	public HasValue<String> getModelTextBox() {
		return modelTextBox;
	}

	@Override
	public HasValue<String> getProducentTextBox() {
		return producentTextBox;
	}

}
