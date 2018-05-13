package ga.saha.gwt.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import ga.saha.gwt.client.presenter.MainPresenter;
import ga.saha.gwt.client.view.MainView;

public class AppController {

    private MainPresenter mainPresenter;

    public AppController(SimpleEventBus eventBus){
        mainPresenter = new MainPresenter(new MainView(), eventBus);
    }

    public void goTo(HasWidgets page){
        mainPresenter.go(page);
    }
}
