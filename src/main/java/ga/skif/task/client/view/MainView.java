package ga.skif.task.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ga.skif.task.client.entity.Dogovor;
import ga.skif.task.client.presenter.MainPresenter;

import java.util.Iterator;
import java.util.List;

import static ga.skif.task.client.Mytask.dateFormat;

public class MainView implements HasWidgets, MainPresenter.Display {
    VerticalPanel container;
    HorizontalPanel firstRowPanel;
    Button createButton;
    Button openButton;
    CellTable<Dogovor> cellTable;
    final SingleSelectionModel<Dogovor> dogovorSelectionModel;

    public MainView() {
        container = new VerticalPanel();
        container.setStyleName("root");
        firstRowPanel = new HorizontalPanel();
        firstRowPanel.setSpacing(5);
        createButton = new Button("Создать договор");
        openButton = new Button("Открыть договор");
        firstRowPanel.add(createButton);
        firstRowPanel.add(openButton);
        container.add(firstRowPanel);
        cellTable = new CellTable<>();
        cellTable.setWidth("100%");
        container.add(cellTable);
        TextColumn<Dogovor> idColumn = new TextColumn<Dogovor>() {
            @Override
            public String getValue(Dogovor dogovor) {
                return dogovor.getId().toString();
            }
        };
        cellTable.addColumn(idColumn, "Серия-Номер");
        TextColumn<Dogovor> dateZaklColumn = new TextColumn<Dogovor>() {
            @Override
            public String getValue(Dogovor dogovor) {
                return dateFormat.format(dogovor.getDataZakl());
            }
        };
        cellTable.addColumn(dateZaklColumn, "Дата заключения");
        TextColumn<Dogovor> strahovatelColumn = new TextColumn<Dogovor>() {
            @Override
            public String getValue(Dogovor dogovor) {
                return dogovor.getStrahovatel().getFullName();
            }
        };
        cellTable.addColumn(strahovatelColumn, "Страхователь");
        TextColumn<Dogovor> premiyaColumn = new TextColumn<Dogovor>() {
            @Override
            public String getValue(Dogovor dogovor) {
                return dogovor.getPremiya();
            }
        };
        cellTable.addColumn(premiyaColumn, "Премия");
        TextColumn<Dogovor> srokColumn = new TextColumn<Dogovor>() {
            @Override
            public String getValue(Dogovor dogovor) {
                return dateFormat.format(dogovor.getStart())
                        + " - " + dateFormat.format(dogovor.getEnd());
            }
        };
        cellTable.addColumn(srokColumn, "Срок действия");

        dogovorSelectionModel = new SingleSelectionModel<>();
        cellTable.setSelectionModel(dogovorSelectionModel);

//        cellTable.setRowDataCellTable(list);
    }

    @Override
    public Widget asWidget() {
        return container;
    }

    @Override
    public MainView getViewInstance() {
        return this;
    }

    @Override
    public void add(Widget w) {
        container.add(w);
    }

    @Override
    public void clear() {
        container.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return container.iterator();
    }

    @Override
    public boolean remove(Widget w) {
        return container.remove(w);
    }

    @Override
    public HasClickHandlers setOpenButtonHandler() {
        return openButton;
    }

    @Override
    public HasClickHandlers setCreateButtonHandler() {
        return createButton;
    }

    @Override
    public SelectionChangeEvent.HasSelectionChangedHandlers setSelectionModelCellTable() {
        return dogovorSelectionModel;
    }

    @Override
    public void setRowDataCellTable(List<Dogovor> list) {
        cellTable.setRowData(list);
    }

}
