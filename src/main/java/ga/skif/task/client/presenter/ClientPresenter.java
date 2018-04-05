package ga.skif.task.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import ga.skif.task.client.view.ClientView;
import ga.skif.task.client.entity.Strahovatel;
import ga.skif.task.client.event.ChooseClientEvent;

import static ga.skif.task.client.Mytask.clickClient;
import static ga.skif.task.client.Mytask.greetingService;
import static ga.skif.task.client.Mytask.strahovatel;

public class ClientPresenter {

    public interface Display {
        HasClickHandlers getCloseButtonHandler();

        HasClickHandlers getSaveButtonHandler();

        Widget asWidget();

        ClientView getViewInstance();
    }

    final Display display;
    final SimpleEventBus eventBus;

    public ClientPresenter(Display display, SimpleEventBus eventBus) {
        this.display = display;
        this.eventBus = eventBus;
        init();
    }

    public void init() {

        ClientView d = display.getViewInstance();

        d.getDialogClient().center();

//        Window.alert(strahovatel.toString());

        try {
            d.getTextBoxFamily1().setText(strahovatel.getLastName());
            d.getTextBoxName1().setText(strahovatel.getFirstName());
            d.getTextBoxName21().setText(strahovatel.getFirstName2());
            d.getDateBoxDataRozhdeniya2().setValue(strahovatel.getBirth());
            d.getTextBoxPassportSeriya2().setText(strahovatel.getPassportSeria().toString());
            d.getTextBoxPassportNomer2().setText(strahovatel.getPassportNumber().toString());
        } catch (Exception ignored) {
        }

        display.getCloseButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                d.getDialogClient().hide();
            }
        });

        display.getSaveButtonHandler().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
//                String id = strahovatel[0].getId();
                Strahovatel strah = new Strahovatel();
                strah.setLastName(d.getTextBoxFamily1().getText());
                strah.setFirstName(d.getTextBoxName21().getText());
                strah.setFirstName2(d.getTextBoxName21().getText());
                strah.setBirth(d.getDateBoxDataRozhdeniya2().getValue());
                strah.setPassportSeria(Integer.valueOf(d.getTextBoxPassportSeriya2().getText()));
                strah.setPassportNumber(Integer.valueOf(d.getTextBoxPassportNomer2().getText()));

                if (clickClient.equals("update")) {
                    greetingService.updateStrahovatelById(strahovatel.getId(), strah,
                            new AsyncCallback<Boolean>() {

                                @Override
                                public void onFailure(Throwable throwable) {
                                    Window.alert("Не сохранено");
                                }

                                @Override
                                public void onSuccess(Boolean status) {
                                    if (status) {
                                        strahovatel.setLastName(strah.getLastName());
                                        strahovatel.setFirstName(strah.getFirstName());
                                        strahovatel.setFirstName2(strah.getFirstName2());
                                        strahovatel.setBirth(strah.getBirth());
                                        strahovatel.setPassportSeria(strah.getPassportSeria());
                                        strahovatel.setPassportNumber(strah.getPassportNumber());
                                        eventBus.fireEvent(new ChooseClientEvent());
                                        d.getDialogClient().hide();
                                    } else {
                                        Window.alert("Не сохранено");
                                    }
                                }
                            });
                }
                if (clickClient.equals("create")){
                    greetingService.saveStrahovatel(strah, new AsyncCallback<Boolean>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            Window.alert("Не сохранено");
                        }
                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            d.getDialogClient().hide();
                        }
                    });
                }
            }
        });
    }
}
