package ga.skif.task.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.Iterator;
import java.util.List;

public class MainView implements HasWidgets, MainPresenter.Display {
    VerticalPanel container;
    HorizontalPanel firstRowPanel;
//    HorizontalPanel rightPanel;
    //    Button logout;
    Button createDogovorButton;
    Button openDogovorButton;
    CellTable<Dogovor> cellTable;
    private final DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
//    private MainView instance;

    public MainView() {
        firstRowPanel = new HorizontalPanel();
        container = new VerticalPanel();
        createDogovorButton = new Button("Создать договор");
        openDogovorButton = new Button("Открыть договор");
        cellTable = new CellTable<>();
        firstRowPanel.add(createDogovorButton);
        firstRowPanel.add(openDogovorButton);
        container.add(firstRowPanel);
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

//        cellTable.setRowDataCellTable(list);
    }

    @Override
    public Widget asWidget() {
        return container;
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
    public void setOpenButtonHandler(ClickHandler openHandler) {
        openDogovorButton.addClickHandler(openHandler);
    }

    @Override
    public void setCreateButtonHandler(ClickHandler createHandler) {
        createDogovorButton.addClickHandler(createHandler);
    }

    @Override
    public void setSelectionModelCellTable(SingleSelectionModel<Dogovor> dogovorSelectionModel) {
        cellTable.setSelectionModel(dogovorSelectionModel);
    }

    @Override
    public void setRowDataCellTable(List<Dogovor> list) {
        cellTable.setRowData(list);
    }

}
