package com.example.gwtmvpdemo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowLaptopsEvent extends GwtEvent<ShowLaptopsEventHandler> {

	public static Type<ShowLaptopsEventHandler> TYPE = new Type<ShowLaptopsEventHandler>();

	@Override
	public Type<ShowLaptopsEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowLaptopsEventHandler handler) {
		handler.onShowLaptops(this);
	}

}
