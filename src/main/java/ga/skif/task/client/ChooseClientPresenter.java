package ga.skif.task.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;
import ga.skif.task.client.entity.AddressOb;
import ga.skif.task.client.entity.Dogovor;
import ga.skif.task.client.entity.Strahovatel;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static ga.skif.task.client.Mytask.*;
import static java.util.Arrays.asList;

public class ChooseClientPresenter {

    public interface Display {
//        HasClickHandlers closeButtonHandler();
//        HasClickHandlers raschetButtonHandler();
//        HasClickHandlers saveButtonHandler();
//        HasKeyUpHandlers numberKeyUpHandler();

        Widget asWidget();
        ChooseClientView getViewInstance();
    }

    final Display display;
    final HandlerManager eventBus;
//    final DateTimeFormat yearFormat = DateTimeFormat.getFormat("yyyy");
//    final Date today = new Date();


    public ChooseClientPresenter(Display display, HandlerManager eventBus) {
        this.display = display;
        this.eventBus = eventBus;
//        Window.alert("DogovorPresenter "+existDogovor.toString());
        init();
//        if (existDogovor.getId().toString().length() > 0) {
//            display.getViewInstance().getTextBoxNomerDogovora().setReadOnly(true);
//            display.getViewInstance().getDateBoxDataZakluchenDogovora().setValue(existDogovor.getDataZakl());
//        } else {
//            display.getViewInstance().getTextBoxNomerDogovora().setReadOnly(false);
//            display.getViewInstance().getDateBoxDataZakluchenDogovora().setValue(today);
//        }
    }

    public void init() {

        ChooseClientView d = display.getViewInstance();

        d.getDialogVibor().center();
//        strahTable.setRowData(asList());
        d.getDataProvider().setList(asList());

        d.getTextBoxFamily().setText(strahovatel.getLastName());
        d.getTextBoxName().setText(strahovatel.getFirstName());
        d.getTextBoxName2().setText(strahovatel.getFirstName2());

//        display.closeButtonHandler().addClickHandler(new ClickHandler() {
        d.getCloseButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                d.getDialogVibor().hide();
            }
        });

        d.getSearchBtn().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                greetingService.greetSearch(d.getTextBoxName().getText(), d.getTextBoxName2().getText(), d.getTextBoxFamily().getText(),
                        new AsyncCallback<List<Strahovatel>>() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                Window.alert("Поиск неудачен");
                            }

                            @Override
                            public void onSuccess(List<Strahovatel> strahovatels) {
                                if (strahovatels.size()==0){
                                    Window.alert("Ничего не найдено.");
//                                    strahTable.setRowData(strahovatels);
                                    d.getDataProvider().setList(strahovatels);
                                }else {
                                    //Window.alert("Поиск удачен. " + strahovatels.get(0).getFullName());
//                                    strahTable.setRowData(strahovatels);
                                    d.getDataProvider().setList(strahovatels);
                                }
                            }
                        });
            }
        });

        d.getNewBtn().addClickHandler(new CreateClient(d.getStrahTable()));

        final SingleSelectionModel<Strahovatel> selectionModel = new SingleSelectionModel<>();
        d.getStrahTable().setSelectionModel(selectionModel);

        d.getChooseBtn().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                //TextBox textBoxFIO, DateBox dateBoxDataRozhdeniya, TextBox textBoxPassportSeriya, TextBox textBoxPassportNomer
                Strahovatel strah = selectionModel.getSelectedObject();
                if (!strah.getFullName().equals("  ")) {
//                    textBoxFIO.setText(strah.getFullName());
//                    dateBoxDataRozhdeniya.setValue(strah.getBirth());
//                    textBoxPassportSeriya.setText(strah.getPassportSeria().toString());
//                    textBoxPassportNomer.setText(strah.getPassportNumber().toString());


                    strahovatel.setId(strah.getId());
                    strahovatel.setFirstName(strah.getFirstName());
                    strahovatel.setFirstName2(strah.getFirstName2());
                    strahovatel.setLastName(strah.getLastName());
                    strahovatel.setBirth(strah.getBirth());
                    strahovatel.setPassportSeria(strah.getPassportSeria());
                    strahovatel.setPassportNumber(strah.getPassportNumber());
                    d.getDialogVibor().hide();
                }
            }
        });
    }


    public void go(final HasWidgets container) {
        init();
//        container.clear();
//        container.add(display.asWidget());

    }

}
