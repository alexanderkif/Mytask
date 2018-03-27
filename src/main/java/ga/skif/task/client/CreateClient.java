package ga.skif.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.DateBox;

import static java.util.Arrays.asList;


public class CreateClient implements ClickHandler, KeyUpHandler {

    private GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    DialogBox dialogCreateClient = new DialogBox();

    DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");

    @Override
    public void onClick(ClickEvent clickEvent) {
        dialogCreateClient.center();
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            dialogCreateClient.center();
        }
    }

    CreateClient(CellTable<Strahovatel> strahTable) {
        dialogCreateClient.setText("Новый клиент");
        dialogCreateClient.setAnimationEnabled(true);

        AbsolutePanel absolutePanel2 = new AbsolutePanel();
        absolutePanel2.setSize("700px", "200px");

        Label label_1 = new Label("ФИО");
        absolutePanel2.add(label_1, 10, 25);
        label_1.setSize("30px", "24px");

        TextBox textBoxFamily = new TextBox();
        absolutePanel2.add(textBoxFamily, 60, 17);
        textBoxFamily.setSize("190px", "24px");

        TextBox textBoxName = new TextBox();
        absolutePanel2.add(textBoxName, 270, 17);
        textBoxName.setSize("190px", "24px");

        TextBox textBoxName2 = new TextBox();
        absolutePanel2.add(textBoxName2, 480, 17);
        textBoxName2.setSize("190px", "24px");

        Label label_2 = new Label("(фамилия)");
        label_2.setStyleName("gwt-Label-mini");
        absolutePanel2.add(label_2, 130, 55);
        label_2.setSize("68px", "16px");

        Label label_3 = new Label("(имя)");
        label_3.setStyleName("gwt-Label-mini");
        absolutePanel2.add(label_3, 340, 55);
        label_3.setSize("68px", "16px");

        Label label_4 = new Label("(отчество)");
        label_4.setStyleName("gwt-Label-mini");
        absolutePanel2.add(label_4, 550, 55);
        label_4.setSize("68px", "16px");

        Label label_11 = new Label("Дата рождения");
        absolutePanel2.add(label_11, 30, 110);
        label_11.setSize("120px", "24px");

        DateBox dateBoxDataRozhdeniya = new DateBox();
        dateBoxDataRozhdeniya.setFormat(new DateBox.DefaultFormat(dateFormat));
        absolutePanel2.add(dateBoxDataRozhdeniya, 130, 100);
        dateBoxDataRozhdeniya.setSize("80px", "16px");

        Label label_12 = new Label("Паспорт серия");
        absolutePanel2.add(label_12, 260, 110);
        label_12.setSize("100px", "24px");

        TextBox textBoxPassportSeriya = new TextBox();
        absolutePanel2.add(textBoxPassportSeriya, 360, 100);
        textBoxPassportSeriya.setSize("80px", "20px");

        Label label_13 = new Label("№");
        absolutePanel2.add(label_13, 460, 110);
        label_13.setSize("30px", "24px");

        TextBox textBoxPassportNomer = new TextBox();
        absolutePanel2.add(textBoxPassportNomer, 490, 100);
        textBoxPassportNomer.setSize("180px", "20px");

        final Button saveBtn = new Button("Сохранить");
        saveBtn.getElement().setId("chooseBtn");
        absolutePanel2.add(saveBtn, 250, 160);

        final Button closeButton = new Button("Отменить");
        closeButton.getElement().setId("closeButton");
        absolutePanel2.add(closeButton, 350, 160);

        dialogCreateClient.setWidget(absolutePanel2);

        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogCreateClient.hide();
            }
        });

        saveBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Strahovatel strahovatel = new Strahovatel();
                strahovatel.setLastName(textBoxFamily.getText());
                strahovatel.setFirstName(textBoxName.getText());
                strahovatel.setFirstName2(textBoxName2.getText());
                strahovatel.setBirth(dateBoxDataRozhdeniya.getValue());
                strahovatel.setPassportSeria(Integer.valueOf(textBoxPassportSeriya.getText()));
                strahovatel.setPassportNumber(Integer.valueOf(textBoxPassportNomer.getText()));
                greetingService.greetSave(strahovatel,
                        new AsyncCallback<Boolean>() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                Window.alert("Не сохранено");
                            }

                            @Override
                            public void onSuccess(Boolean status) {
                                if (status) {
                                    strahTable.setRowData(asList(strahovatel));
                                    dialogCreateClient.hide();
                                } else {
                                    Window.alert("Не сохранено");
                                }
                            }
                        });
            }
        });
    }
}
