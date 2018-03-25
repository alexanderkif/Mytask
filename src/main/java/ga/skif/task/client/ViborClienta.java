package ga.skif.task.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

public class ViborClienta  implements ClickHandler, KeyUpHandler {

    final DialogBox dialogVibor = new DialogBox();

    @Override
    public void onClick(ClickEvent clickEvent) {
        dialogVibor.center();
    }

    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            dialogVibor.center();
        }
    }

    ViborClienta(){

        dialogVibor.setText("Выбор клиентов");
        dialogVibor.setAnimationEnabled(true);

        AbsolutePanel absolutePanel1 = new AbsolutePanel();
        absolutePanel1.setSize("580px", "300px");

        Label label_1 = new Label("ФИО");
        absolutePanel1.add(label_1, 10, 25);
        label_1.setSize("30px", "24px");

        TextBox textBoxFamily = new TextBox();
        absolutePanel1.add(textBoxFamily, 60, 17);
        textBoxFamily.setSize("130px", "24px");

        TextBox textBoxName = new TextBox();
        absolutePanel1.add(textBoxName, 210, 17);
        textBoxName.setSize("130px", "24px");

        TextBox textBoxName2 = new TextBox();
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
    }
}
