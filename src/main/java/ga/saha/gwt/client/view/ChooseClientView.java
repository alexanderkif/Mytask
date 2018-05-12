package ga.saha.gwt.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ga.saha.gwt.client.presenter.ChooseClientPresenter;
import ga.saha.gwt.shared.Strahovatel;

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

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel.setSpacing(5);
//        verticalPanel.setWidth("700px");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        horizontalPanel.setSpacing(5);
        verticalPanel.add(horizontalPanel);

        Label label_1 = new Label("ФИО");
        horizontalPanel.add(label_1);
        label_1.setSize("30px", "24px");
        label_1.setStyleName("gwt-label-dog");

        textBoxFamily = new TextBox();
        horizontalPanel.add(textBoxFamily);
        textBoxFamily.setSize("200px", "24px");

        textBoxName = new TextBox();
        horizontalPanel.add(textBoxName);
        textBoxName.setSize("200px", "24px");

        textBoxName2 = new TextBox();
        horizontalPanel.add(textBoxName2);
        textBoxName2.setSize("200px", "24px");

        searchBtn = new Button("Поиск");
        searchBtn.getElement().setId("searchBtn");
        horizontalPanel.add(searchBtn);

        HorizontalPanel labelPanel = new HorizontalPanel();
        labelPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelPanel.setSpacing(5);
        verticalPanel.add(labelPanel);

        Label label_2 = new Label("(фамилия)");
        label_2.setStyleName("gwt-Label-mini");
        labelPanel.add(label_2);
        label_2.setSize("200px", "16px");

        Label label_3 = new Label("(имя)");
        label_3.setStyleName("gwt-Label-mini");
        labelPanel.add(label_3);
        label_3.setSize("200px", "16px");

        Label label_4 = new Label("(отчество)");
        label_4.setStyleName("gwt-Label-mini");
        labelPanel.add(label_4);
        label_4.setSize("200px", "16px");

        HorizontalPanel tablePanel = new HorizontalPanel();
        tablePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        tablePanel.setWidth("100%");
        tablePanel.setSpacing(5);
        verticalPanel.add(tablePanel);

        strahTable = new CellTable<>();
        strahTable.setWidth("90%");
        tablePanel.add(strahTable);

        HorizontalPanel pagerPanel = new HorizontalPanel();
        pagerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        pagerPanel.setSpacing(5);
        verticalPanel.add(pagerPanel);

        dataProvider.addDataDisplay(strahTable);
//        dataProvider.setList(strahovatels);
        SimplePager pager = new SimplePager();
        pagerPanel.add(pager);
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
                return strahovatel.getPassportseria().toString()+" №"+strahovatel.getPassportnumber().toString();}
        };
        strahTable.addColumn(passportColumn,"Паспортные данные");

        strahTable.setWidth("90%");
        strahTable.setColumnWidth(fioColumn, "50%");
        strahTable.setColumnWidth(dateBirthColumn, "20%");
        strahTable.setColumnWidth(passportColumn, "30%");

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        buttonPanel.setSpacing(5);
        verticalPanel.add(buttonPanel);

        chooseBtn = new Button("Выбрать");
        chooseBtn.getElement().setId("chooseBtn");
        buttonPanel.add(chooseBtn);

        newBtn = new Button("Новый");
        newBtn.getElement().setId("newBtn");
        buttonPanel.add(newBtn);

        closeButton = new Button("Закрыть");
        closeButton.getElement().setId("closeButton");
        buttonPanel.add(closeButton);

        dialogVibor.setWidget(verticalPanel);

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
    public HasClickHandlers newButtonHandler() {
        return newBtn;
    }

    @Override
    public HasClickHandlers closeButtonHandler() {
        return closeButton;
    }

    @Override
    public HasClickHandlers searchButtonHandler() {
        return searchBtn;
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

//    public Button getSearchBtn() {
//        return searchBtn;
//    }

//    public Button getChooseBtn() {
//        return chooseBtn;
//    }
//
//    public Button getNewBtn() {
//        return newBtn;
//    }
//
//    public Button getCloseButton() {
//        return closeButton;
//    }

    public DialogBox getDialogVibor() {
        return dialogVibor;
    }
}
