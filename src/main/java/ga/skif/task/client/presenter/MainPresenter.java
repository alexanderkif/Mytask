package ga.skif.task.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ga.skif.task.client.event.ListUpdateEvent;
import ga.skif.task.client.event.ListUpdateEventHandler;
import ga.skif.task.client.view.DogovorView;
import ga.skif.task.client.view.MainView;
import ga.skif.task.client.entity.Dogovor;

import java.util.List;

import static ga.skif.task.client.Mytask.*;

public class MainPresenter {
    private HasWidgets container;

    public interface Display{
        HasClickHandlers setOpenButtonHandler();
        HasClickHandlers setCreateButtonHandler();
        SelectionChangeEvent.HasSelectionChangedHandlers setSelectionModelCellTable();
        Widget asWidget();
        MainView getViewInstance();
    }

    final Display display;
    final SimpleEventBus eventBus;
//    SingleSelectionModel<Dogovor> strahSelectionModel = new SingleSelectionModel<>();

    public MainPresenter(Display display, SimpleEventBus eventBus){
        this.display = display;
        this.eventBus = eventBus;
    }

    public void init(){

        display.setCreateButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                existDogovor = new Dogovor();
                clickDogovor = "create";
                new DogovorPresenter(new DogovorView(), eventBus);
            }
        });

        eventBus.addHandler(ListUpdateEvent.TYPE, new ListUpdateEventHandler(){
            @Override
            public void onListUpdate(ListUpdateEvent chooseClientEvent) {
                display.getViewInstance().getDataProvider().refresh();
            }
        });


//        OpenDogovor handler2 = new OpenDogovor();
        display.setOpenButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                clickDogovor = "open";
                existDogovor = ((SingleSelectionModel<Dogovor>) display.setSelectionModelCellTable()).getSelectedObject();
                if (existDogovor.getId().toString().length()>0)
                        new DogovorPresenter(new DogovorView(), eventBus);
            }
        });

        greetingService.findDogovor(0, new AsyncCallback<List<Dogovor>>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("Не могу загрузить договоры.");
            }

            @Override
            public void onSuccess(List<Dogovor> dlist) {
                list.addAll(dlist);
                display.getViewInstance().getDataProvider().setList(list);
            }
        });

        display.setSelectionModelCellTable().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                existDogovor = ((SingleSelectionModel<Dogovor>) display.setSelectionModelCellTable()).getSelectedObject();
            }
        });
    }

    public void go(final HasWidgets container){
        init();
        this.container = container;
        container.clear();
        container.add(display.asWidget());
    }

}
