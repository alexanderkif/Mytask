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

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel.setSpacing(5);
//        verticalPanel.setWidth("700px");
//        verticalPanel.setSize("700px", "200px");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        horizontalPanel.setSpacing(5);

        Label label_1 = new Label("ФИО");
        horizontalPanel.add(label_1);
        label_1.setSize("30px", "24px");
        label_1.setStyleName("gwt-label-dog");

        textBoxFamily1 = new TextBox();
        horizontalPanel.add(textBoxFamily1);
        textBoxFamily1.setSize("200px", "24px");

        textBoxName1 = new TextBox();
        horizontalPanel.add(textBoxName1);
        textBoxName1.setSize("200px", "24px");

        textBoxName21 = new TextBox();
        horizontalPanel.add(textBoxName21);
        textBoxName21.setSize("200px", "24px");

        verticalPanel.add(horizontalPanel);

        HorizontalPanel labelPanel = new HorizontalPanel();
        labelPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelPanel.setSpacing(5);

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

        verticalPanel.add(labelPanel);

        HorizontalPanel datePanel = new HorizontalPanel();
        datePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        datePanel.setSpacing(5);

        Label label_11 = new Label("Дата рождения");
        datePanel.add(label_11);
        label_11.setSize("120px", "24px");
        label_11.setStyleName("gwt-label-dog");

        dateBoxDataRozhdeniya2 = new DateBox();
        dateBoxDataRozhdeniya2.setFormat(new DateBox.DefaultFormat(dateFormat));
        datePanel.add(dateBoxDataRozhdeniya2);
        dateBoxDataRozhdeniya2.setSize("80px", "16px");

        Label label_12 = new Label("Паспорт серия");
        datePanel.add(label_12);
        label_12.setSize("100px", "24px");
        label_12.setStyleName("gwt-label-dog");

        textBoxPassportSeriya2 = new TextBox();
        datePanel.add(textBoxPassportSeriya2);
        textBoxPassportSeriya2.setSize("80px", "20px");

        Label label_13 = new Label("№");
        datePanel.add(label_13);
        label_13.setSize("30px", "24px");
        label_13.setStyleName("gwt-label-dog");

        Label label_id = new Label("");
        datePanel.add(label_id);
//        label_id.setVisible(false);

        textBoxPassportNomer2 = new TextBox();
        datePanel.add(textBoxPassportNomer2);
        textBoxPassportNomer2.setSize("180px", "20px");

        verticalPanel.add(datePanel);

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        buttonPanel.setSpacing(5);

        saveBtn = new Button("Сохранить");
        saveBtn.getElement().setId("saveBtn");
        buttonPanel.add(saveBtn);

        closeButton = new Button("Отменить");
        closeButton.getElement().setId("closeButton");
        buttonPanel.add(closeButton);

        verticalPanel.add(buttonPanel);

        dialogClient.setWidget(verticalPanel);

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
