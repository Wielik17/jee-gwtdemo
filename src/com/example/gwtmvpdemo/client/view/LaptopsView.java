package com.example.gwtmvpdemo.client.view;

import java.util.ArrayList;
import java.util.List;

import com.example.gwtmvpdemo.client.presenter.LaptopsPresenter.Display;
import com.example.gwtmvpdemo.shared.model.Laptop;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class LaptopsView extends Composite implements Display {

	private final Button addLaptopButton = new Button("Add laptop");
	private final Button deleteLaptopButton = new Button("Delete laptop");
	private final Button editLaptopButton = new Button("Edit laptop");
	private final FlexTable laptopsTable;

	public LaptopsView() {

		laptopsTable = new FlexTable();
		VerticalPanel mainPanel = new VerticalPanel();
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.add(addLaptopButton);
		buttonPanel.add(deleteLaptopButton);
		buttonPanel.add(editLaptopButton);
		buttonPanel.addStyleName("buttonPanel");
		initWidget(mainPanel);

		mainPanel.add(buttonPanel);
		mainPanel.add(laptopsTable);
		// Create a table to layout the form options
		laptopsTable.setCellSpacing(6);
		FlexCellFormatter cellFormatter = laptopsTable.getFlexCellFormatter();

		// Add a title to the form
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		
		// Wrap the content in a DecoratorPanel
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(laptopsTable);
		mainPanel.add(decPanel);
	}

	@Override
	public HasClickHandlers getAddButton() {
		return addLaptopButton;
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		return deleteLaptopButton;
	}

	@Override
	public HasClickHandlers getEditButton(){
		return editLaptopButton;
	}
	@Override
	public Widget asWidget() {
		return this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setData(List<Laptop> data) {
		laptopsTable.removeAllRows();
		laptopsTable.setText(0, 0, "ID");
		laptopsTable.setText(0, 1, "Model");
		laptopsTable.setCellPadding(10);
		laptopsTable.setPixelSize(300, 20);
		laptopsTable.setText(0, 2, "Producent");
		laptopsTable.setText(0, 3, "Date");
		laptopsTable.setText(0, 4, "Select");

		for (int i = 0; i < data.size(); i++) {
			Laptop l = data.get(i);
			laptopsTable.setText(i + 1, 0, String.valueOf(l.getId()));
			laptopsTable.setText(i + 1, 1, l.getModel());
			laptopsTable.setText(i + 1, 2, l.getProducent());
			laptopsTable.setText(i + 1, 3,String.valueOf(l.getDatebox().getDate()) + "-" + String.valueOf(l.getDatebox().getMonth()) + "-" + (l.getDatebox().getYear()+1900));
			laptopsTable.setWidget(i + 1, 4, new CheckBox());
		}

	}

	@Override
	public List<Integer> getSelectedRows() {
		List<Integer> selectedId = new ArrayList<Integer>();

		for (int i = 1; i < laptopsTable.getRowCount(); ++i) {
			CheckBox checkBox = (CheckBox) laptopsTable.getWidget(i, 4);
			if (checkBox.getValue()) {
				selectedId.add(Integer.parseInt(laptopsTable.getText(i, 0)));
			}
		}
		return selectedId;
	}

}
