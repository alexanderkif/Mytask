package ga.skif.task.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.List;

import static ga.skif.task.client.Mytask.*;

public class DogovorPresenter {
    public interface Display{
//        void setOpenButtonHandler(ClickHandler openHandler);
//        void setCreateButtonHandler(ClickHandler clickHandler);
//        void setSelectionModelCellTable(SingleSelectionModel<Dogovor> dogovorSelectionModel);
//        void setRowDataCellTable(List<Dogovor> list);
        Widget asWidget();
//        MainView getViewInstance();
//        Button getButton();
//        CellTable<Dogovor> getCellTable();
    }

    final Display display;
    final HandlerManager eventBus;

    public DogovorPresenter(Display display, HandlerManager eventBus){
        this.display = display;
        this.eventBus = eventBus;
    }

    public void init(){
//        CreateDogovor handler = new CreateDogovor();
//        display.setCreateButtonHandler(handler);
//        OpenDogovor handler2 = new OpenDogovor();
//        display.setOpenButtonHandler(handler2);
//        greetingService.findDogovor(0, new AsyncCallback<List<Dogovor>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                Window.alert("Не могу загрузить договоры.");
//            }
//
//            @Override
//            public void onSuccess(List<Dogovor> dlist) {
//                list.addAll(dlist);
//                display.setRowDataCellTable(list);
//            }
//        });
//        final SingleSelectionModel<Dogovor> dogovorSelectionModel = new SingleSelectionModel<>();
//        display.setSelectionModelCellTable(dogovorSelectionModel);
//        dogovorSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//            @Override
//            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
//                existDogovor = dogovorSelectionModel.getSelectedObject();
//            }
//        });
    }

    public void go(final HasWidgets container){
        init();
        container.clear();
        container.add(display.asWidget());
    }

    public Display getView(){
        return display;
    }
}
