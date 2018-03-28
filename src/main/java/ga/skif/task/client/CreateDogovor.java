package ga.skif.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static ga.skif.task.client.Mytask.strahovatel;

public class CreateDogovor implements ClickHandler, KeyUpHandler {

    private GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    private DialogBox dialogBox = new DialogBox();

    public void onClick(ClickEvent event) {
        dialogBox.center();
    }

    public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            dialogBox.center();
        }
    }

    CreateDogovor() {

        final Date today = new Date();

        final DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
        final DateTimeFormat yearFormat = DateTimeFormat.getFormat("yyyy");

        dialogBox.setText("Форма для ввода данных");
        dialogBox.setAnimationEnabled(true);

        AbsolutePanel absolutePanel = new AbsolutePanel();
        absolutePanel.setSize("860px", "740px");

        AbsolutePanel raschetPanel = new AbsolutePanel();
        raschetPanel.setStyleName("dialogVPanel");
        absolutePanel.add(raschetPanel, 10, 28);
        raschetPanel.setSize("840px", "232px");
        raschetPanel.setStyleName("raschetPanel");

        Label raschetLabel = new Label("Расчет");
        raschetLabel.setWidth("60px");
        absolutePanel.add(raschetLabel, 33, 19);
        raschetLabel.setStyleName("raschetLabel");

        Label strSummaLabel = new Label("Страховая сумма");
        raschetPanel.add(strSummaLabel, 10, 18);
        strSummaLabel.setSize("150px", "24px");

        TextBox strSumma = new TextBox();
        raschetPanel.add(strSumma, 166, 10);
        strSumma.setSize("155px", "20px");

        Label label = new Label("Тип недвижимости");
        raschetPanel.add(label, 10, 56);
        label.setSize("150px", "24px");

        Label error = new Label("");
        error.setStyleName("error_text");
        raschetPanel.add(error, 380, 60);
        error.setSize("400px", "80px");

        TextBox textBoxGodPostroiki = new TextBox();
        raschetPanel.add(textBoxGodPostroiki, 166, 86);
        textBoxGodPostroiki.setSize("155px", "20px");

        Label label_1 = new Label("Год постройки");
        raschetPanel.add(label_1, 10, 94);
        label_1.setSize("150px", "24px");

        TextBox textBoxPloshadb = new TextBox();
        raschetPanel.add(textBoxPloshadb, 166, 124);
        textBoxPloshadb.setSize("155px", "20px");

        Label label_2 = new Label("Площадь, кв.м");
        raschetPanel.add(label_2, 10, 132);
        label_2.setSize("150px", "24px");

        Label label_3 = new Label("Срок действия с");
        raschetPanel.add(label_3, 377, 16);
        label_3.setSize("137px", "24px");

        ListBox comboBoxTipNedvizhimosti = new ListBox();
        comboBoxTipNedvizhimosti.addItem("");
        comboBoxTipNedvizhimosti.addItem("Квартира");
        comboBoxTipNedvizhimosti.addItem("Дом");
        comboBoxTipNedvizhimosti.addItem("Комната");
        raschetPanel.add(comboBoxTipNedvizhimosti, 165, 48);
        comboBoxTipNedvizhimosti.setSize("166px", "32px");

        DateBox dateBoxStart = new DateBox();
        dateBoxStart.setFormat(new DateBox.DefaultFormat(dateFormat));
        dateBoxStart.setValue(new Date());
        raschetPanel.add(dateBoxStart, 506, 10);
        dateBoxStart.setSize("84px", "16px");

        Label label_4 = new Label("по");
        raschetPanel.add(label_4, 624, 18);
        label_4.setSize("32px", "24px");

        DateBox dateBoxEnd = new DateBox();
        dateBoxEnd.setFormat(new DateBox.DefaultFormat(dateFormat));
        raschetPanel.add(dateBoxEnd, 665, 10);
        dateBoxEnd.setSize("84px", "16px");

        Button btnRasschitat = new Button("New button");
        btnRasschitat.setText("Рассчитать");
        raschetPanel.add(btnRasschitat, 352, 162);

        Label label_5 = new Label("Дата расчета");
        raschetPanel.add(label_5, 10, 198);
        label_5.setSize("150px", "24px");

        Label label_6 = new Label("Премия");
        raschetPanel.add(label_6, 494, 198);
        label_6.setSize("94px", "24px");

        TextBox textBoxPremiya = new TextBox();
        raschetPanel.add(textBoxPremiya, 594, 190);
        textBoxPremiya.setSize("155px", "20px");
        textBoxPremiya.setReadOnly(true);

        DateBox dateBoxDataRascheta = new DateBox();
        dateBoxDataRascheta.setFormat(new DateBox.DefaultFormat(dateFormat));
        raschetPanel.add(dateBoxDataRascheta, 166, 194);
        dateBoxDataRascheta.setSize("84px", "16px");
        dateBoxDataRascheta.setEnabled(false);

        Label label_7 = new Label("№ договора");
        absolutePanel.add(label_7, 32, 290);
        label_7.setSize("150px", "24px");

        Label dogovorExist = new Label("");
        absolutePanel.add(dogovorExist,5,5);
        dogovorExist.setVisible(false);

        TextBox textBoxNomerDogovora = new TextBox();
        absolutePanel.add(textBoxNomerDogovora, 188, 282);
        textBoxNomerDogovora.setSize("155px", "20px");
        textBoxNomerDogovora.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                if (textBoxNomerDogovora.getText().length()==6){
                    greetingService.checkDogId(Integer.valueOf(textBoxNomerDogovora.getText()),
                            new AsyncCallback<Boolean>(){

                                @Override
                                public void onFailure(Throwable throwable) {
//                                    Window.alert("error");
                                    dogovorExist.setText("error");
                                }

                                @Override
                                public void onSuccess(Boolean b) {
                                    if (b) {
//                                        Window.alert("yes b="+b);
                                        dogovorExist.setText("yes");
                                        textBoxNomerDogovora.setStyleName("error");
                                    }
                                    else {
//                                        Window.alert("no b="+b);
                                        dogovorExist.setText("no");
                                        textBoxNomerDogovora.setStyleName("noerror");
                                    }
                                }
                            });
                }else{
                    textBoxNomerDogovora.setStyleName("error");
                }
            }
        });

        Label label_8 = new Label("Дата заключения");
        absolutePanel.add(label_8, 412, 290);
        label_8.setSize("137px", "24px");

        DateBox dateBoxDataZakluchenDogovora = new DateBox();
        dateBoxDataZakluchenDogovora.setFormat(new DateBox.DefaultFormat(dateFormat));
        absolutePanel.add(dateBoxDataZakluchenDogovora, 541, 284);
        dateBoxDataZakluchenDogovora.setSize("84px", "16px");
        dateBoxDataZakluchenDogovora.setEnabled(false);
        dateBoxDataZakluchenDogovora.setValue(today);

        Label label_9 = new Label("СТРАХОВАТЕЛЬ");
        label_9.setStyleName("gwt-Label-big");
        absolutePanel.add(label_9, 32, 331);
        label_9.setSize("228px", "24px");

        Button buttonVibratb = new Button("Выбрать");
        absolutePanel.add(buttonVibratb, 32, 361);
        buttonVibratb.setSize("75px", "28px");

        Label label_10 = new Label("ФИО");
        absolutePanel.add(label_10, 135, 365);
        label_10.setSize("47px", "24px");

        TextBox textBoxFIO = new TextBox();
        absolutePanel.add(textBoxFIO, 187, 357);
        textBoxFIO.setSize("559px", "20px");
//        textBoxFIO.setText(strahovatel.getFullName());
        textBoxFIO.setReadOnly(true);

        Button buttonChange = new Button("Изменить");
        buttonChange.setText("Изменить");
        absolutePanel.add(buttonChange, 762, 361);
        buttonChange.setSize("84px", "28px");

        Label label_11 = new Label("Дата рождения");
        absolutePanel.add(label_11, 32, 406);
        label_11.setSize("137px", "24px");

        DateBox dateBoxDataRozhdeniya = new DateBox();
        dateBoxDataRozhdeniya.setFormat(new DateBox.DefaultFormat(dateFormat));
        absolutePanel.add(dateBoxDataRozhdeniya, 161, 400);
        dateBoxDataRozhdeniya.setSize("84px", "16px");
//        dateBoxDataRozhdeniya.setValue(strahovatel.getBirth());

        Label label_12 = new Label("Паспорт серия");
        absolutePanel.add(label_12, 350, 406);
        label_12.setSize("121px", "24px");

        TextBox textBoxPassportSeriya = new TextBox();
        absolutePanel.add(textBoxPassportSeriya, 477, 398);
        textBoxPassportSeriya.setSize("105px", "20px");
//        textBoxPassportSeriya.setText(String.valueOf(strahovatel.getPassportSeria()));

        Label label_13 = new Label("№");
        absolutePanel.add(label_13, 617, 406);
        label_13.setSize("30px", "24px");

        TextBox textBoxPassportNomer = new TextBox();
        absolutePanel.add(textBoxPassportNomer, 653, 398);
        textBoxPassportNomer.setSize("183px", "20px");
//        textBoxPassportNomer.setText(String.valueOf(strahovatel.getPassportNumber()));

        Label label_14 = new Label("Адрес недвижимости");
        label_14.setStyleName("gwt-Label-big");
        absolutePanel.add(label_14, 32, 436);
        label_14.setSize("321px", "24px");

        ListBox listBoxCountries = new ListBox();
        listBoxCountries.addItem("");
        listBoxCountries.addItem("Россия");
        listBoxCountries.addItem("Белоруссия");
        listBoxCountries.addItem("Казахстан");
        absolutePanel.add(listBoxCountries, 32, 466);
        listBoxCountries.setSize("166px", "32px");

        TextBox textBoxIndex = new TextBox();
        absolutePanel.add(textBoxIndex, 204, 466);
        textBoxIndex.setSize("91px", "20px");

        TextBox textBoxRespKraiObl = new TextBox();
        absolutePanel.add(textBoxRespKraiObl, 311, 466);
        textBoxRespKraiObl.setSize("228px", "20px");

        TextBox textBoxRayon = new TextBox();
        absolutePanel.add(textBoxRayon, 555, 466);
        textBoxRayon.setSize("281px", "20px");

        TextBox textBoxNaselPunkt = new TextBox();
        absolutePanel.add(textBoxNaselPunkt, 32, 525);
        textBoxNaselPunkt.setSize("155px", "20px");

        TextBox textBoxStreet = new TextBox();
        absolutePanel.add(textBoxStreet, 204, 525);
        textBoxStreet.setSize("281px", "20px");

        TextBox textBoxDom = new TextBox();
        absolutePanel.add(textBoxDom, 501, 525);
        textBoxDom.setSize("74px", "20px");

        TextBox textBoxKorpus = new TextBox();
        absolutePanel.add(textBoxKorpus, 590, 525);
        textBoxKorpus.setSize("74px", "20px");

        TextBox textBoxStroenie = new TextBox();
        absolutePanel.add(textBoxStroenie, 680, 525);
        textBoxStroenie.setSize("65px", "20px");

        TextBox textBoxKvartira = new TextBox();
        absolutePanel.add(textBoxKvartira, 762, 525);
        textBoxKvartira.setSize("74px", "20px");

        Label label_15 = new Label("государство");
        label_15.setStyleName("gwt-Label-mini");
        absolutePanel.add(label_15, 45, 495);
        label_15.setSize("107px", "16px");

        Label label_16 = new Label("индекс");
        label_16.setStyleName("gwt-Label-mini");
        absolutePanel.add(label_16, 230, 495);
        label_16.setSize("75px", "16px");

        Label label_17 = new Label("республика, край, область");
        label_17.setStyleName("gwt-Label-mini");
        absolutePanel.add(label_17, 336, 495);
        label_17.setSize("193px", "16px");

        Label label_18 = new Label("район");
        label_18.setStyleName("gwt-Label-mini");
        absolutePanel.add(label_18, 648, 495);
        label_18.setSize("107px", "16px");

        Label label_19 = new Label("населенный пункт");
        label_19.setStyleName("gwt-Label-mini");
        absolutePanel.add(label_19, 45, 554);
        label_19.setSize("137px", "16px");

        Label label_20 = new Label("улица");
        label_20.setStyleName("gwt-Label-mini");
        absolutePanel.add(label_20, 325, 554);
        label_20.setSize("107px", "16px");

        Label label_21 = new Label("дом");
        label_21.setStyleName("gwt-Label-mini");
        absolutePanel.add(label_21, 530, 554);
        label_21.setSize("55px", "16px");

        Label label_22 = new Label("корпус");
        label_22.setStyleName("gwt-Label-mini");
        absolutePanel.add(label_22, 606, 554);
        label_22.setSize("68px", "16px");

        Label label_23 = new Label("строение");
        label_23.setStyleName("gwt-Label-mini");
        absolutePanel.add(label_23, 688, 554);
        label_23.setSize("68px", "16px");

        Label label_24 = new Label("квартира");
        label_24.setStyleName("gwt-Label-mini");
        absolutePanel.add(label_24, 772, 554);
        label_24.setSize("68px", "16px");

        Label label_25 = new Label("КОММЕНТАРИЙ");
        label_25.setStyleName("gwt-Label-big");
        absolutePanel.add(label_25, 32, 576);
        label_25.setSize("228px", "24px");

        Label label_26 = new Label("Комментарий к договору (не печатается на полисе)");
        absolutePanel.add(label_26, 32, 606);
        label_26.setSize("137px", "84px");

        TextArea textAreaComment = new TextArea();
        absolutePanel.add(textAreaComment, 188, 606);
        textAreaComment.setSize("648px", "70px");

//        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        final Button saveButton = new Button("Сохранить");
        saveButton.getElement().setId("saveButton");
        absolutePanel.add(saveButton, 400, 700);

        final Button closeButton = new Button("К списку договоров");
        closeButton.getElement().setId("closeButton");
        absolutePanel.add(closeButton, 500, 700);
        dialogBox.setWidget(absolutePanel);

        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });

        //Кнопка Рассчитать
        btnRasschitat.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

                Integer strSum = 0;
                Double koTH = 0.0, koGP = 0.0, koPL = 0.0;
                String errString = "";

                if (!isInteger(strSumma.getText())) {
                    errString += "Страховая сумма должна быть целым числом. ";
                } else {
                    strSum = Integer.valueOf(strSumma.getText());
                }

                if (Objects.equals(comboBoxTipNedvizhimosti.getSelectedItemText(), "")) {
                    errString += "Тип недвижимости должен быть выбран. ";
                } else {
                    switch (comboBoxTipNedvizhimosti.getSelectedItemText()) {
                        case "Квартира":
                            koTH = 1.7;
                            break;
                        case "Дом":
                            koTH = 1.5;
                            break;
                        default:
                            koTH = 1.3;
                    }
                }

                if (!isInteger(textBoxGodPostroiki.getText()) || textBoxGodPostroiki.getText().length() != 4) {
                    errString += "Введите год постройки (4 цифры). ";
                } else {
                    if (Integer.valueOf(textBoxGodPostroiki.getText())>Integer.valueOf(yearFormat.format(today))) {
                        errString += "Дом еще не построен. ";
                    }else{
                        if (Integer.valueOf(textBoxGodPostroiki.getText()) < 2000) {
                            koGP = 1.3;
                        } else {
                            if (Integer.valueOf(textBoxGodPostroiki.getText()) < 2014) {
                                koGP = 1.6;
                            } else {
                                koGP = 2.0;
                            }
                        }
                    }
                }

                if (!isNumeric(textBoxPloshadb.getText())) {
                    errString += "Введите правильно площадь. ";
                } else {
                    if (Double.valueOf(textBoxPloshadb.getText()) < 50) {
                        koPL = 1.2;
                    } else {
                        if (Double.valueOf(textBoxPloshadb.getText()) < 100) {
                            koPL = 1.5;
                        } else {
                            koPL = 2.0;
                        }
                    }
                }

                if (dateBoxStart.getValue().getTime() - today.getTime() < -1000*60*60*24){
                    errString += "Дата начала договора не может быть меньше текущей даты. ";
                }

                if (dateBoxEnd.getValue().getTime() - dateBoxStart.getValue().getTime() < 0){
                    errString += "Дата окончания договора не может быть меньше даты начала. ";
                }

                if (TimeUnit.DAYS.convert(
                        dateBoxEnd.getValue().getTime() - dateBoxStart.getValue().getTime(),
                        TimeUnit.MILLISECONDS)>365){
                    errString += "Договор не может действовать дольше года. ";
                }

                error.setText(errString);

                if (errString.equals("")) {
                    dateBoxDataRascheta.setValue(today);
                    //Страховая премия = (Страховая сумма / кол-во дней) * Коэф.ТН * Коэф.ГП * Коэф.Пл
                    double prem = ((int) (strSum * koTH * koGP * koPL * 100 /TimeUnit.DAYS.convert(
                                    dateBoxEnd.getValue().getTime() - dateBoxStart.getValue().getTime(),
                                    TimeUnit.MILLISECONDS)))/100.0;
                    textBoxPremiya.setText(String.valueOf(prem));
                }
            }
        });

        ViborClienta viborClienta = new ViborClienta(textBoxFIO,dateBoxDataRozhdeniya,textBoxPassportSeriya,textBoxPassportNomer);
        buttonVibratb.addClickHandler(viborClienta);

        ChangeClient changeClient = new ChangeClient(textBoxFIO,dateBoxDataRozhdeniya,textBoxPassportSeriya,textBoxPassportNomer);
        buttonChange.addClickHandler(changeClient);

        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                AddressOb addressOb = new AddressOb(listBoxCountries.getSelectedItemText(),textBoxIndex.getText(),
                        textBoxRespKraiObl.getText(),textBoxRayon.getText(),textBoxNaselPunkt.getText(),
                        textBoxStreet.getText(),Integer.valueOf(textBoxDom.getText()),textBoxKorpus.getText(),
                        textBoxStroenie.getText(),Integer.valueOf(textBoxKvartira.getText()),
                        textAreaComment.getText());
                Dogovor dogovor = new Dogovor();
                dogovor.setId(Integer.valueOf(textBoxNomerDogovora.getText()));
                dogovor.setDataZakl(dateBoxDataZakluchenDogovora.getValue());
                dogovor.setStrahovatel(strahovatel);
                dogovor.setAddressOb(addressOb);
                dogovor.setStart(dateBoxStart.getValue());
                dogovor.setEnd(dateBoxEnd.getValue());
                dogovor.setStrSumma(Integer.valueOf(strSumma.getText()));
                dogovor.setType(comboBoxTipNedvizhimosti.getSelectedItemText());
                dogovor.setYear(textBoxGodPostroiki.getText());
                dogovor.setSquair(textBoxPloshadb.getText());
                dogovor.setPremiya(textBoxPremiya.getText());
                dogovor.setDateRasheta(dateBoxDataRascheta.getValue());

                if (dogovorExist.getText().equals("no")){
                    greetingService.createDogovor(dogovor, new AsyncCallback<Boolean>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            Window.alert("Не сохранено");
                        }

                        @Override
                        public void onSuccess(Boolean aBoolean) {
//                            Window.alert("Cохранено");
                            Mytask.list.add(dogovor);
                            dialogBox.hide();
                        }
                    });
                }

                if (dogovorExist.getText().equals("yes")){
                    //greetingService.updateDogovor()
                }
            }
        });
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String str) {
        try {
            double d = Double.parseDouble(str);
            if (d % 1 == 0) {
                return true;
            }
        } catch (NumberFormatException ignored) {
        }
        return false;
    }
}
