package ga.skif.task.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;

public class AppController {
    HandlerManager eventBus;
    MainPresenter mainPresenter;
    HasWidgets container;

    public AppController(HandlerManager manager){
        this.eventBus = manager;
        mainPresenter = new MainPresenter(new MainView(), eventBus);
        bindEvents();
    }

    public void bindEvents(){
//        eventBus.addHandler(CreateDogovorEvent.TYPE, new CreateDogovorEventHandler(){
//            @Override
//            public void onMain(CreateDogovorEvent event) {
//                // TODO Auto-generated method stub
//                //if Main successful
//                MainPresenter mainpage = new MainPresenter(new MainView(), eventBus);
//                container = mainpage.getView().getViewInstance();
//                mainpage.go(RootPanel.get());
//            }
//        });


//        eventBus.addHandler(CreateButtonEvent.TYPE, new CreateDogovorEventHandler(){
//            @Override
//            public void onCreate(CreateDogovorEvent event) {
//                mainPresenter.go(RootPanel.get());
//            }
//        });
    }

    public void goTo(HasWidgets page){
        this.container = page;
        mainPresenter.go(page);
    }
}
