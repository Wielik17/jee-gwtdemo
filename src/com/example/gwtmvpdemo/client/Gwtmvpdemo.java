package com.example.gwtmvpdemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwtmvpdemo implements EntryPoint {

	public void onModuleLoad() {
		HandlerManager eventBus = new HandlerManager(null);
		
		StorageService storage = new StorageService();
		
		// Other dependencies, services here
	    AppControler appViewer = new AppControler(storage, eventBus);
	    
	    appViewer.go(RootPanel.get());
	}
}
