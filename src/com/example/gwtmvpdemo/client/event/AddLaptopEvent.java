package com.example.gwtmvpdemo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddLaptopEvent extends GwtEvent<AddLaptopEventHandler> {

	public static Type<AddLaptopEventHandler> TYPE = new Type<AddLaptopEventHandler>();

	@Override
	public Type<AddLaptopEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddLaptopEventHandler handler) {
		handler.onAddLaptop(this);
	}

}
