package ga.skif.task.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ga.skif.task.client.entity.Strahovatel;
import ga.skif.task.client.presenter.ChooseClientPresenter;

import java.util.Iterator;

public class ChooseClientView implements HasWidgets, ChooseClientPresenter.Display {

    private DialogBox dialogVibor = new DialogBox();
    private CellTable<Strahovatel> strahTable;
    private ListDataProvider<Strahovatel> dataProvider = new ListDataProvider<Strahovatel>();
    private TextBox textBoxFamily;
    private TextBox textBoxName;
    private TextBox textBoxName2;
    private Button searchBtn;
    private Button chooseBtn;
    private Button newBtn;
    private Button closeButton;
    final SingleSelectionModel<Strahovatel> strahSelectionModel;

    public ChooseClientView(){

        dialogVibor.setText("Выбор клиентов");
        dialogVibor.setAnimationEnabled(true);

        AbsolutePanel absolutePanel1 = new AbsolutePanel();
        absolutePanel1.setSize("580px", "300px");

        Label label_1 = new Label("ФИО");
        absolutePanel1.add(label_1, 10, 25);
        label_1.setSize("30px", "24px");

        textBoxFamily = new TextBox();
        absolutePanel1.add(textBoxFamily, 60, 17);
        textBoxFamily.setSize("130px", "24px");

        textBoxName = new TextBox();
        absolutePanel1.add(textBoxName, 210, 17);
        textBoxName.setSize("130px", "24px");

        textBoxName2 = new TextBox();
        absolutePanel1.add(textBoxName2, 360, 17);
        textBoxName2.setSize("130px", "24px");

        Label label_2 = new Label("(фамилия)");
        label_2.setStyleName("gwt-Label-mini");
        absolutePanel1.add(label_2, 85, 55);
        label_2.setSize("68px", "16px");

        Label label_3 = new Label("(имя)");
        label_3.setStyleName("gwt-Label-mini");
        absolutePanel1.add(label_3, 235, 55);
        label_3.setSize("68px", "16px");

        Label label_4 = new Label("(отчество)");
        label_4.setStyleName("gwt-Label-mini");
        absolutePanel1.add(label_4, 385, 55);
        label_4.setSize("68px", "16px");

        searchBtn = new Button("Поиск");
        searchBtn.getElement().setId("searchBtn");
        absolutePanel1.add(searchBtn, 510, 20);

        strahTable = new CellTable<>();
        absolutePanel1.add(strahTable, 20, 80);

        dataProvider.addDataDisplay(strahTable);
//        dataProvider.setList(strahovatels);
        SimplePager pager = new SimplePager();
        absolutePanel1.add(pager,200,235);
        pager.setDisplay(strahTable);
        strahTable.setPageSize(5);

        TextColumn<Strahovatel> fioColumn = new TextColumn<Strahovatel>() {
            @Override
            public String getValue(Strahovatel strahovatel) {
                return strahovatel.getFullName();
            }
        };
        strahTable.addColumn(fioColumn,"ФИО");

        TextColumn<Strahovatel> dateBirthColumn = new TextColumn<Strahovatel>() {
            @Override
            public String getValue(Strahovatel strahovatel) { return DateTimeFormat.getFormat(
                    DateTimeFormat.PredefinedFormat.DATE_SHORT).format(strahovatel.getBirth());}
        };
        strahTable.addColumn(dateBirthColumn,"Дата рождения");

        TextColumn<Strahovatel> passportColumn = new TextColumn<Strahovatel>() {
            @Override
            public String getValue(Strahovatel strahovatel) {
                return strahovatel.getPassportSeria().toString()+" №"+strahovatel.getPassportNumber().toString();}
        };
        strahTable.addColumn(passportColumn,"Паспортные данные");

        strahTable.setWidth("90%");
        strahTable.setColumnWidth(fioColumn, "50%");
        strahTable.setColumnWidth(dateBirthColumn, "20%");
        strahTable.setColumnWidth(passportColumn, "30%");

        chooseBtn = new Button("Выбрать");
        chooseBtn.getElement().setId("chooseBtn");
        absolutePanel1.add(chooseBtn, 170, 270);

        newBtn = new Button("Новый");
        newBtn.getElement().setId("newBtn");
        absolutePanel1.add(newBtn, 250, 270);

        closeButton = new Button("Закрыть");
        closeButton.getElement().setId("closeButton");
        absolutePanel1.add(closeButton, 320, 270);

        dialogVibor.setWidget(absolutePanel1);

        strahSelectionModel = new SingleSelectionModel<>();
        strahTable.setSelectionModel(strahSelectionModel);

    }


    @Override
    public void add(Widget widget) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<Widget> iterator() {
        return null;
    }

    @Override
    public boolean remove(Widget widget) {
        return false;
    }

    @Override
    public HasClickHandlers chooseBtnHandler() {
        return chooseBtn;
    }

    @Override
    public SelectionChangeEvent.HasSelectionChangedHandlers setSelectionModelCellTable() {
        return strahSelectionModel;
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public ChooseClientView getViewInstance() {
        return this;
    }

    public CellTable<Strahovatel> getStrahTable() {
        return strahTable;
    }

    public ListDataProvider<Strahovatel> getDataProvider() {
        return dataProvider;
    }

    public TextBox getTextBoxFamily() {
        return textBoxFamily;
    }

    public TextBox getTextBoxName() {
        return textBoxName;
    }

    public TextBox getTextBoxName2() {
        return textBoxName2;
    }

    public Button getSearchBtn() {
        return searchBtn;
    }

    public Button getChooseBtn() {
        return chooseBtn;
    }

    public Button getNewBtn() {
        return newBtn;
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public DialogBox getDialogVibor() {
        return dialogVibor;
    }
}
