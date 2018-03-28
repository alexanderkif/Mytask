package ga.skif.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Mytask implements EntryPoint {

    public static Strahovatel strahovatel = new Strahovatel("","","");

    private GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private final DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");

    static List<Dogovor> list = new ArrayList<>();

    private CellTable<Dogovor> cellTable = new CellTable<>();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        RootPanel rootPanel = RootPanel.get("vp");

        Integer id = 0;
//        Label labelll = new Label();

        greetingService.findDogovor(id, new AsyncCallback<List<Dogovor>>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("Не могу загрузить договоры.");
            }

            @Override
            public void onSuccess(List<Dogovor> dlist) {
                list.addAll(dlist);
                cellTable.setRowData(list);
//                labelll.setText(dlist.get(0).getId().toString());
            }
        });

        final Button txtbtnCreate = new Button("Создать договор");
        rootPanel.add(txtbtnCreate, 130, 20);

        final Button txtbtnOpen = new Button("Открыть договор");
        rootPanel.add(txtbtnOpen, 270, 20);

//        labelll.setText(list.get(0).getId().toString());
//        rootPanel.add(labelll,5,5);

        rootPanel.add(cellTable, 130, 60);
        cellTable.setPageSize(10);

        final SingleSelectionModel<Dogovor> selectionModel = new SingleSelectionModel<>();
        cellTable.setSelectionModel(selectionModel);

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

        cellTable.setRowData(list);

        // Add a handler to send the name to the server
        CreateDogovor handler = new CreateDogovor();
        txtbtnCreate.addClickHandler(handler);

    }
}
