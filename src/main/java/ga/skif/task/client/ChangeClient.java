package ga.skif.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import ga.skif.task.client.entity.Strahovatel;
import ga.skif.task.client.service.GreetingService;
import ga.skif.task.client.service.GreetingServiceAsync;

import static ga.skif.task.client.Mytask.strahovatel;

public class ChangeClient implements ClickHandler, KeyUpHandler {
    private GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private DialogBox dialogCangeClient = new DialogBox();

    private TextBox textBoxFamily1;
    private TextBox textBoxName1;
    private TextBox textBoxName21;
    private DateBox dateBoxDataRozhdeniya2;
    private TextBox textBoxPassportSeriya2;
    private TextBox textBoxPassportNomer2;

    private final DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");

    @Override
    public void onClick(ClickEvent clickEvent) {
        dialogCangeClient.center();
//        Window.alert("changeClient "+strahovatel.getFullName());

        textBoxFamily1.setText(strahovatel.getLastName());
        textBoxName1.setText(strahovatel.getFirstName());
        textBoxName21.setText(strahovatel.getFirstName2());
        dateBoxDataRozhdeniya2.setValue(strahovatel.getBirth());
        textBoxPassportSeriya2.setText(strahovatel.getPassportSeria().toString());
        textBoxPassportNomer2.setText(strahovatel.getPassportNumber().toString());
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            dialogCangeClient.center();
        }
    }

    public ChangeClient(TextBox textBoxFIO1, DateBox dateBoxDataRozhdeniya1, TextBox textBoxPassportSeriya1, TextBox textBoxPassportNomer1) {

        dialogCangeClient.setText("Клиент");
        dialogCangeClient.setAnimationEnabled(true);

//        final Strahovatel[] strahovatel = new Strahovatel[1];

        AbsolutePanel absolutePanel2 = new AbsolutePanel();
        absolutePanel2.setSize("700px", "200px");



        Label label_1 = new Label("ФИО");
        absolutePanel2.add(label_1, 10, 25);
        label_1.setSize("30px", "24px");

        textBoxFamily1 = new TextBox();
        absolutePanel2.add(textBoxFamily1, 60, 17);
        textBoxFamily1.setSize("190px", "24px");

        textBoxName1 = new TextBox();
        absolutePanel2.add(textBoxName1, 270, 17);
        textBoxName1.setSize("190px", "24px");

        textBoxName21 = new TextBox();
        absolutePanel2.add(textBoxName21, 480, 17);
        textBoxName21.setSize("190px", "24px");

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

        dateBoxDataRozhdeniya2 = new DateBox();
        dateBoxDataRozhdeniya2.setFormat(new DateBox.DefaultFormat(dateFormat));
        absolutePanel2.add(dateBoxDataRozhdeniya2, 130, 100);
        dateBoxDataRozhdeniya2.setSize("80px", "16px");

        Label label_12 = new Label("Паспорт серия");
        absolutePanel2.add(label_12, 260, 110);
        label_12.setSize("100px", "24px");

        textBoxPassportSeriya2 = new TextBox();
        absolutePanel2.add(textBoxPassportSeriya2, 360, 100);
        textBoxPassportSeriya2.setSize("80px", "20px");

        Label label_13 = new Label("№");
        absolutePanel2.add(label_13, 460, 110);
        label_13.setSize("30px", "24px");

        Label label_id = new Label("");
        absolutePanel2.add(label_id, 10, 3);
//        label_id.setVisible(false);

        textBoxPassportNomer2 = new TextBox();
        absolutePanel2.add(textBoxPassportNomer2, 490, 100);
        textBoxPassportNomer2.setSize("180px", "20px");

        final Button saveBtn = new Button("Сохранить");
        saveBtn.getElement().setId("chooseBtn");
        absolutePanel2.add(saveBtn, 250, 160);

        final Button closeButton = new Button("Отменить");
        closeButton.getElement().setId("closeButton");
        absolutePanel2.add(closeButton, 350, 160);

        dialogCangeClient.setWidget(absolutePanel2);

//        if (!textBoxFIO1.getText().equals("  ")) {
//            String fam = textBoxFIO1.getText().split(" ")[0];
//            String nam = textBoxFIO1.getText().split(" ")[1];
//            String nam2 = textBoxFIO1.getText().split(" ")[2];
//            Date bir = dateBoxDataRozhdeniya1.getValue();
//            greetingService.greetSearchFirst(nam, nam2, fam, bir, new AsyncCallback<Strahovatel>() {
//                @Override
//                public void onFailure(Throwable throwable) {
//                    Window.alert("Не нашел");
//                }
//
//                @Override
//                public void onSuccess(Strahovatel first) {
////                    strahovatel[0] = first;
//                    Window.alert("Нашел id="+first.getId());
//                    label_id.setText(first.getId());
//                    textBoxFamily1.setText(first.getLastName());
//                    textBoxName1.setValue(first.getFirstName());
//                    textBoxName21.setValue(first.getFirstName2());
//                    dateBoxDataRozhdeniya2.setValue(first.getBirth());
//                    textBoxPassportSeriya2.setText(first.getPassportSeria().toString());
//                    textBoxPassportNomer2.setValue(first.getPassportNumber().toString());
//                }
//            });
//        }

        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogCangeClient.hide();
            }
        });

        saveBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
//                String id = strahovatel[0].getId();
                Strahovatel strah = new Strahovatel();
                strah.setLastName(textBoxFamily1.getText());
                strah.setFirstName(textBoxName1.getText());
                strah.setFirstName2(textBoxName21.getText());
                strah.setBirth(dateBoxDataRozhdeniya2.getValue());
                strah.setPassportSeria(Integer.valueOf(textBoxPassportSeriya2.getText()));
                strah.setPassportNumber(Integer.valueOf(textBoxPassportNomer2.getText()));

                greetingService.greetUpdate(strahovatel.getId(), strah,
                        new AsyncCallback<Boolean>() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                Window.alert("Не сохранено");
                            }

                            @Override
                            public void onSuccess(Boolean status) {
                                if (status) {
                                    textBoxFIO1.setText(strah.getFullName());
                                    dateBoxDataRozhdeniya1.setValue(strah.getBirth());
                                    textBoxPassportSeriya1.setText(strah.getPassportSeria().toString());
                                    textBoxPassportNomer1.setText(strah.getPassportNumber().toString());
//                                    Window.alert("status "+status+"   "+strah.getFullName());
                                    dialogCangeClient.hide();
                                } else {
                                    Window.alert("Не сохранено");
                                }
                            }
                        });
            }
        });
    }
}
