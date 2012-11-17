
package com.example.gwtmvpdemo.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;

public class EditLaptopEvent extends GwtEvent<EditLaptopEventHandler> {

	public static Type<EditLaptopEventHandler> TYPE = new Type<EditLaptopEventHandler>();
	private int id;

	public EditLaptopEvent(int id) {
		this.id = id;
	}

	public Integer getId() { return id; }
	
	@Override
	public Type<EditLaptopEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditLaptopEventHandler handler) {
		handler.onEditLaptop(this);
	}

}
