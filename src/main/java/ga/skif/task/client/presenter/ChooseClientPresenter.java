package ga.skif.task.client.presenter;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ga.skif.task.client.view.ChooseClientView;
import ga.skif.task.client.CreateClient;
import ga.skif.task.client.entity.Strahovatel;
import ga.skif.task.client.event.ChooseClientEvent;

import java.util.List;

import static ga.skif.task.client.Mytask.*;
import static java.util.Arrays.asList;

public class ChooseClientPresenter {

    public interface Display {
        HasClickHandlers chooseBtnHandler();
        SelectionChangeEvent.HasSelectionChangedHandlers setSelectionModelCellTable();
//        HasClickHandlers raschetButtonHandler();
//        HasClickHandlers saveButtonHandler();
//        HasKeyUpHandlers numberKeyUpHandler();

        Widget asWidget();
        ChooseClientView getViewInstance();
    }

    final Display display;
    final SimpleEventBus eventBus;
//    final DateTimeFormat yearFormat = DateTimeFormat.getFormat("yyyy");
//    final Date today = new Date();


    public ChooseClientPresenter(Display display, SimpleEventBus eventBus) {
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

        display.chooseBtnHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                eventBus.fireEvent(new ChooseClientEvent());
                d.getDialogVibor().hide();
            }
        });

        d.getDialogVibor().center();
        d.getDataProvider().setList(asList());

//        d.getTextBoxFamily().setText(strahovatel.getLastName());
//        d.getTextBoxName().setText(strahovatel.getFirstName());
//        d.getTextBoxName2().setText(strahovatel.getFirstName2());

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

//        final SingleSelectionModel<Strahovatel> selectionModel = new SingleSelectionModel<>();
//        d.getStrahTable().setSelectionModel(selectionModel);

        d.setSelectionModelCellTable().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                Strahovatel strah = ((SingleSelectionModel<Strahovatel>)display.setSelectionModelCellTable()).getSelectedObject();
                if (!strah.getFullName().equals("  ")) {
                    strahovatel.setId(strah.getId());
                    strahovatel.setFirstName(strah.getFirstName());
                    strahovatel.setFirstName2(strah.getFirstName2());
                    strahovatel.setLastName(strah.getLastName());
                    strahovatel.setBirth(strah.getBirth());
                    strahovatel.setPassportSeria(strah.getPassportSeria());
                    strahovatel.setPassportNumber(strah.getPassportNumber());

                    d.getTextBoxFamily().setText(strahovatel.getLastName());
                    d.getTextBoxName().setText(strahovatel.getFirstName());
                    d.getTextBoxName2().setText(strahovatel.getFirstName2());
                }
            }
        });
    }
}
