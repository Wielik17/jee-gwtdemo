package com.example.gwtmvpdemo.client.view;

import com.example.gwtmvpdemo.client.presenter.AddLaptopPresenter.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.datepicker.client.DateBox;
import com.smartgwt.client.widgets.Slider;

public class AddLaptopView extends Composite implements Display {

	private final Button showLaptopsButton;
	private final Button addLaptop;
	private final TextBox modelTextBox;
	private final TextBox producentTextBox;
	private final FlexTable layout;
	private final int rowCount;
	private final Label modelLabel;
	private final Label producentLabel;
	private final Label dateLabel;
	private final DateBox dateBox;
	//final Slider vSlider = new Slider("Rating"); 

	public AddLaptopView() {
		modelTextBox = new TextBox();
		producentTextBox = new TextBox();
		modelLabel = new Label("model:");
		producentLabel = new Label("producent:");
		dateLabel = new Label("date:");
		VerticalPanel mainPanel = new VerticalPanel();
		initWidget(mainPanel);
		showLaptopsButton = new Button("Show laptops");
		addLaptop = new Button("Add");

		// Create a table to layout the form options
		layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
		modelTextBox.setText("k61IC");
		producentTextBox.setText("Asus");
		// Add a title to the form
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		// Create a DateBox
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
		layout.setWidget(4, 0, addLaptop);
		layout.setWidget(4, 1, showLaptopsButton);

		// Wrap the content in a DecoratorPanel
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(layout);
		mainPanel.add(decPanel);
		rowCount = layout.getRowCount();
	}

	@Override
	public HasClickHandlers getShowLaptopsButton() {
		return showLaptopsButton;
	}

	@Override
	public HasClickHandlers getAddLaptopsButton() {
		return addLaptop;
	}

	@Override
	public HasValue<String> getModelTextBox() {
		return modelTextBox;
	}

	@Override
	public HasValue<String> getProducentTextBox() {
		return producentTextBox;
	}

	@Override
	public DateBox getDateBox() {
		return dateBox;
	}

	@Override
	public boolean checkEmpty() {

		boolean flaga = true;
		for (int i = 1; i < layout.getRowCount() - 1; i++) {
			Label empty = new Label("this input cannot be empty");
			empty.setStyleName("emptyLabel");

			if (i == 3) {
				DateBox datebox = (DateBox) layout.getWidget(i, 1);
				if(datebox.getValue() == null){
					flaga = false;
					layout.setWidget(i, 2, empty);
					datebox.setStyleName("emptyInput");
				} else {
					if (layout.isCellPresent(i, 2)) {
						layout.clearCell(i, 2);
						datebox.removeStyleName("emptyInput");
					}
				}
			} else {
				TextBox input = (TextBox) layout.getWidget(i, 1);
				if (input.getValue().isEmpty()) {
					layout.setWidget(i, 2, empty);
					input.setStyleName("emptyInput");
					flaga = false;
				} else {
					if (layout.isCellPresent(i, 2)) {
						layout.clearCell(i, 2);
						input.removeStyleName("emptyInput");
					}
				}
			}
		}

		if (flaga) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean existMessage() {
		Label exist = new Label("This Laptop exist in your collection");
		exist.setStyleName("existLabel");
		if (!layout.isCellPresent(rowCount, 1)) {
			layout.setWidget(layout.getRowCount() - 1, 2, exist);
		}
		return true;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
