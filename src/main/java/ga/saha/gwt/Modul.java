package ga.saha.gwt;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;
import ga.saha.gwt.client.AppController;

public class Modul implements EntryPoint {

    public void onModuleLoad() {

		SimpleEventBus eventBus = new SimpleEventBus();
		AppController app = new AppController(eventBus);
		app.goTo(RootPanel.get());
	}
}