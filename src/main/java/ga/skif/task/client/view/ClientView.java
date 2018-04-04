package ga.skif.task.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import ga.skif.task.client.presenter.ClientPresenter;

import java.util.Date;
import java.util.Iterator;

import static ga.skif.task.client.Mytask.dateFormat;

public class ClientView implements HasWidgets, ClientPresenter.Display {

    private DialogBox dialogClient = new DialogBox();
    private TextBox textBoxFamily1;
    private TextBox textBoxName1;
    private TextBox textBoxName21;
    private DateBox dateBoxDataRozhdeniya2;
    private TextBox textBoxPassportSeriya2;
    private TextBox textBoxPassportNomer2;
    private Button saveBtn;
    private Button closeButton;

    public ClientView() {

        dialogClient.setText("Клиент");
        dialogClient.setAnimationEnabled(true);

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

        saveBtn = new Button("Сохранить");
        saveBtn.getElement().setId("saveBtn");
        absolutePanel2.add(saveBtn, 250, 160);

        closeButton = new Button("Отменить");
        closeButton.getElement().setId("closeButton");
        absolutePanel2.add(closeButton, 350, 160);

        dialogClient.setWidget(absolutePanel2);

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
    public HasClickHandlers getCloseButtonHandler() {
        return closeButton;
    }

    @Override
    public HasClickHandlers getSaveButtonHandler() {
        return saveBtn;
    }

    @Override
    public Widget asWidget() {
        return null;
    }

    @Override
    public ClientView getViewInstance() {
        return this;
    }

    public DialogBox getDialogClient() {
        return dialogClient;
    }

    public TextBox getTextBoxFamily1() {
        return textBoxFamily1;
    }

    public TextBox getTextBoxName1() {
        return textBoxName1;
    }

    public TextBox getTextBoxName21() {
        return textBoxName21;
    }

    public void setDateBoxDataRozhdeniya2(Date date) {
        dateBoxDataRozhdeniya2.setValue(date);
    }

    public DateBox getDateBoxDataRozhdeniya2() {
        return dateBoxDataRozhdeniya2;
    }

    public TextBox getTextBoxPassportSeriya2() {
        return textBoxPassportSeriya2;
    }

    public TextBox getTextBoxPassportNomer2() {
        return textBoxPassportNomer2;
    }
}
