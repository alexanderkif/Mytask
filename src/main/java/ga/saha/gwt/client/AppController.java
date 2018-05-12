package ga.saha.gwt.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HasWidgets;
import ga.saha.gwt.client.presenter.MainPresenter;
import ga.saha.gwt.client.view.MainView;
import ga.saha.gwt.shared.Strahovatel;

import java.util.List;

import static java.util.Arrays.asList;

public class AppController {

    SimpleEventBus eventBus;
    MainPresenter mainPresenter;
    HasWidgets container;

    public AppController(SimpleEventBus eventBus){
        this.eventBus = eventBus;
        mainPresenter = new MainPresenter(new MainView(), eventBus);
    }

    public void goTo(HasWidgets page){
        this.container = page;
        mainPresenter.go(page);
    }
}
