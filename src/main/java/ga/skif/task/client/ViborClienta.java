package ga.skif.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.SingleSelectionModel;
import ga.skif.task.server.GreetingServiceImpl;

import java.util.ArrayList;
import java.util.List;
import static ga.skif.task.shared.FieldVerifier.strahovatel;
import static java.util.Arrays.asList;

public class ViborClienta  implements ClickHandler, KeyUpHandler {

    private GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    DialogBox dialogVibor = new DialogBox();
    CellTable<Strahovatel> strahTable;
    TextBox textBoxFamily;
    TextBox textBoxName;
    TextBox textBoxName2;

    @Override
    public void onClick(ClickEvent clickEvent) {
        dialogVibor.center();
        strahTable.setRowData(asList());

        textBoxFamily.setText(strahovatel.getLastName());
        textBoxName.setText(strahovatel.getFirstName());
        textBoxName2.setText(strahovatel.getFirstName2());
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            dialogVibor.center();
        }
    }

    ViborClienta(TextBox textBoxFIO, DateBox dateBoxDataRozhdeniya, TextBox textBoxPassportSeriya, TextBox textBoxPassportNomer){

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

        final Button searchBtn = new Button("Поиск");
        searchBtn.getElement().setId("searchBtn");
        absolutePanel1.add(searchBtn, 510, 20);

        strahTable = new CellTable<>();
        absolutePanel1.add(strahTable, 20, 90);

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
        strahTable.setRowData(new ArrayList<>());

        final Button chooseBtn = new Button("Выбрать");
        chooseBtn.getElement().setId("chooseBtn");
        absolutePanel1.add(chooseBtn, 170, 270);

        final Button newBtn = new Button("Новый");
        newBtn.getElement().setId("newBtn");
        absolutePanel1.add(newBtn, 250, 270);

        final Button closeButton = new Button("Закрыть");
        closeButton.getElement().setId("closeButton");
        absolutePanel1.add(closeButton, 320, 270);

        dialogVibor.setWidget(absolutePanel1);

        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogVibor.hide();
            }
        });

        searchBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                greetingService.greetSearch(textBoxName.getText(), textBoxName2.getText(), textBoxFamily.getText(),
                        new AsyncCallback<List<Strahovatel>>() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                Window.alert("Поиск неудачен");
                            }

                            @Override
                            public void onSuccess(List<Strahovatel> strahovatels) {
                                if (strahovatels.size()==0){
                                    Window.alert("Ничего не найдено.");
                                    strahTable.setRowData(strahovatels);
                                }else {
                                    //Window.alert("Поиск удачен. " + strahovatels.get(0).getFullName());
                                    strahTable.setRowData(strahovatels);
                                }
                            }
                        });
            }
        });

        CreateClient createClient = new CreateClient(strahTable);
        newBtn.addClickHandler(createClient);

        final SingleSelectionModel<Strahovatel> selectionModel = new SingleSelectionModel<>();
        strahTable.setSelectionModel(selectionModel);

        chooseBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                //TextBox textBoxFIO, DateBox dateBoxDataRozhdeniya, TextBox textBoxPassportSeriya, TextBox textBoxPassportNomer
                Strahovatel strah = selectionModel.getSelectedObject();
                if (!strah.getFullName().equals("  ")) {
                    textBoxFIO.setText(strah.getFullName());
                    dateBoxDataRozhdeniya.setValue(strah.getBirth());
                    textBoxPassportSeriya.setText(strah.getPassportSeria().toString());
                    textBoxPassportNomer.setText(strah.getPassportNumber().toString());

                    strahovatel.setId(strah.getId());
                    strahovatel.setFirstName(strah.getFirstName());
                    strahovatel.setFirstName2(strah.getFirstName2());
                    strahovatel.setLastName(strah.getLastName());
                    strahovatel.setBirth(strah.getBirth());
                    strahovatel.setPassportSeria(strah.getPassportSeria());
                    strahovatel.setPassportNumber(strah.getPassportNumber());
                    dialogVibor.hide();
                }
//                strahovatel.setFirstName("FirstName");
//                strahovatel.setFirstName2(strah.getFirstName2());
//                dialogVibor.hide();
            }
        });
    }



}
