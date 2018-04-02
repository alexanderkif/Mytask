package ga.skif.task.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static ga.skif.task.client.Mytask.*;

public class DogovorView implements HasWidgets, DogovorPresenter.Display {

    private GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
//    private DialogBox openDialogBox = new DialogBox();

    private TextBox strSumma;
    private TextBox textBoxGodPostroiki;
    private TextBox textBoxPloshadb;
    private ListBox comboBoxTipNedvizhimosti;
    private DateBox dateBoxStart;
    private DateBox dateBoxEnd;
    private TextBox textBoxPremiya;
    private DateBox dateBoxDataRascheta;
    private TextBox textBoxNomerDogovora;
    private DateBox dateBoxDataZakluchenDogovora;
    private TextBox textBoxFIO;
    private DateBox dateBoxDataRozhdeniya;
    private TextBox textBoxPassportSeriya;
    private TextBox textBoxPassportNomer;
    private ListBox listBoxCountries;
    private TextBox textBoxIndex;
    private TextBox textBoxRespKraiObl;
    private TextBox textBoxRayon;
    private TextBox textBoxNaselPunkt;
    private TextBox textBoxStreet;
    private TextBox textBoxDom;
    private TextBox textBoxKorpus;
    private TextBox textBoxStroenie;
    private TextBox textBoxKvartira;
    private TextArea textAreaComment;
    VerticalPanel container;


    public void init() {
        strSumma.setText(existDogovor.getStrSumma().toString());
        textBoxGodPostroiki.setText(existDogovor.getYear());
        textBoxPloshadb.setText(existDogovor.getSquair());
        comboBoxTipNedvizhimosti.clear();
        comboBoxTipNedvizhimosti.addItem(existDogovor.getType());
        for (String s : listNedvizhimosti) {
            comboBoxTipNedvizhimosti.addItem(s);
        }
        dateBoxStart.setValue(existDogovor.getStart());
        dateBoxEnd.setValue(existDogovor.getEnd());
        textBoxPremiya.setText(existDogovor.getPremiya());
        dateBoxDataRascheta.setValue(existDogovor.getDateRasheta());
        textBoxNomerDogovora.setText(existDogovor.getId().toString());
        dateBoxDataZakluchenDogovora.setValue(existDogovor.getDataZakl());
        textBoxFIO.setText(existDogovor.getStrahovatel().getFullName());
        dateBoxDataRozhdeniya.setValue(existDogovor.getStrahovatel().getBirth());
        textBoxPassportSeriya.setText(String.valueOf(existDogovor.getStrahovatel().getPassportSeria()));
        textBoxPassportNomer.setText(String.valueOf(existDogovor.getStrahovatel().getPassportNumber()));
//        listBoxCountries.addItem(existDogovor.getAddressOb().getState());
        listBoxCountries.clear();
        listBoxCountries.addItem(existDogovor.getAddressOb().getState());
        for (String s : countries) {
            listBoxCountries.addItem(s);
        }
        textBoxIndex.setText(existDogovor.getAddressOb().getIndex());
        textBoxRespKraiObl.setText(existDogovor.getAddressOb().getKrai());
        textBoxRayon.setText(existDogovor.getAddressOb().getDistrict());
        textBoxNaselPunkt.setText(existDogovor.getAddressOb().getTown());
        textBoxStreet.setText(existDogovor.getAddressOb().getStreet());
        textBoxDom.setText(existDogovor.getAddressOb().getHome().toString());
        textBoxKorpus.setText(existDogovor.getAddressOb().getKorpus());
        textBoxStroenie.setText(existDogovor.getAddressOb().getStroenie());
        textBoxKvartira.setText(existDogovor.getAddressOb().getFlat().toString());
        textAreaComment.setText(existDogovor.getAddressOb().getComment());
        strahovatel = existDogovor.getStrahovatel();
    }

    DogovorView() {

        final Date today = new Date();

        final DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
        final DateTimeFormat yearFormat = DateTimeFormat.getFormat("yyyy");

//        openDialogBox.setText("Форма ввода данных");
//        openDialogBox.setAnimationEnabled(true);

        container = new VerticalPanel();
        HorizontalPanel raschetPanel = new HorizontalPanel();

        raschetPanel.setStyleName("dialogVPanel");
        container.add(raschetPanel);
//        raschetPanel.setSize("840px", "232px");
        raschetPanel.setStyleName("raschetPanel");

        VerticalPanel inRaschetPanel = new VerticalPanel();
        raschetPanel.add(inRaschetPanel);
        HorizontalPanel firstRowInRaschetPanel = new HorizontalPanel();
        inRaschetPanel.add(firstRowInRaschetPanel);
        VerticalPanel leftRaschet = new VerticalPanel();
        firstRowInRaschetPanel.add(leftRaschet);

//        Label raschetLabel = new Label("Расчет");
//        raschetLabel.setWidth("60px");
//        raschetPanel.add(raschetLabel);
//        raschetLabel.setStyleName("raschetLabel");

        HorizontalPanel row1 = new HorizontalPanel();

        Label strSummaLabel = new Label("Страховая сумма");
        row1.add(strSummaLabel);
        strSummaLabel.setSize("150px", "24px");

        strSumma = new TextBox();
        row1.add(strSumma);
        strSumma.setSize("155px", "20px");

        leftRaschet.add(row1);
        HorizontalPanel row2 = new HorizontalPanel();

        Label label = new Label("Тип недвижимости");
        row2.add(label);
        label.setSize("150px", "24px");

        comboBoxTipNedvizhimosti = new ListBox();
        row2.add(comboBoxTipNedvizhimosti);
        comboBoxTipNedvizhimosti.setSize("166px", "32px");

        leftRaschet.add(row2);
        HorizontalPanel row3 = new HorizontalPanel();

        Label label_1 = new Label("Год постройки");
        row3.add(label_1);
        label_1.setSize("150px", "24px");

        textBoxGodPostroiki = new TextBox();
        row3.add(textBoxGodPostroiki);
        textBoxGodPostroiki.setSize("155px", "20px");

        leftRaschet.add(row3);
        HorizontalPanel row4 = new HorizontalPanel();

        Label label_2 = new Label("Площадь, кв.м");
        row4.add(label_2);
        label_2.setSize("150px", "24px");

        textBoxPloshadb = new TextBox();
        row4.add(textBoxPloshadb);
        textBoxPloshadb.setSize("155px", "20px");

        leftRaschet.add(row4);

        VerticalPanel rightRaschet = new VerticalPanel();
        firstRowInRaschetPanel.add(rightRaschet);

        HorizontalPanel row5 = new HorizontalPanel();

        Label label_3 = new Label("Срок действия с");
        row5.add(label_3);
        label_3.setSize("137px", "24px");

        dateBoxStart = new DateBox();
        dateBoxStart.setFormat(new DateBox.DefaultFormat(dateFormat));
//        dateBoxStart.setValue(new Date());
        row5.add(dateBoxStart);
        dateBoxStart.setSize("84px", "16px");

        Label label_4 = new Label("по");
        row5.add(label_4);
        label_4.setSize("32px", "24px");

        dateBoxEnd = new DateBox();
        dateBoxEnd.setFormat(new DateBox.DefaultFormat(dateFormat));
        row5.add(dateBoxEnd);
        dateBoxEnd.setSize("84px", "16px");

        rightRaschet.add(row5);
        HorizontalPanel row6 = new HorizontalPanel();

        Label error = new Label("");
        error.setStyleName("error_text");
        row6.add(error);
        error.setSize("400px", "80px");

        rightRaschet.add(row6);

        Button btnRasschitat = new Button("New button");
        btnRasschitat.setText("Рассчитать");
        inRaschetPanel.add(btnRasschitat);

        HorizontalPanel bottomRashetPanel = new HorizontalPanel();

        Label label_5 = new Label("Дата расчета");
        bottomRashetPanel.add(label_5);
        label_5.setSize("150px", "24px");

        dateBoxDataRascheta = new DateBox();
        dateBoxDataRascheta.setFormat(new DateBox.DefaultFormat(dateFormat));
        bottomRashetPanel.add(dateBoxDataRascheta);
        dateBoxDataRascheta.setSize("84px", "16px");
        dateBoxDataRascheta.setEnabled(false);

        Label label_6 = new Label("Премия");
        bottomRashetPanel.add(label_6);
        label_6.setSize("94px", "24px");

        textBoxPremiya = new TextBox();
        bottomRashetPanel.add(textBoxPremiya);
        textBoxPremiya.setSize("155px", "20px");
        textBoxPremiya.setReadOnly(true);

        inRaschetPanel.add(bottomRashetPanel);

        HorizontalPanel nomerData = new HorizontalPanel();

        Label label_7 = new Label("№ договора");
        nomerData.add(label_7);
        label_7.setSize("150px", "24px");

        Label dogovorExist = new Label("");
//        nomerData.add(dogovorExist);
        dogovorExist.setVisible(false);

        textBoxNomerDogovora = new TextBox();
        nomerData.add(textBoxNomerDogovora);
        textBoxNomerDogovora.setSize("155px", "20px");
        textBoxNomerDogovora.setReadOnly(true);
//        textBoxNomerDogovora.addKeyUpHandler(new KeyUpHandler() {
//            @Override
//            public void onKeyUp(KeyUpEvent keyUpEvent) {
//                if (textBoxNomerDogovora.getText().length()==6){
//                    greetingService.checkDogId(Integer.valueOf(textBoxNomerDogovora.getText()),
//                            new AsyncCallback<Boolean>(){
//
//                                @Override
//                                public void onFailure(Throwable throwable) {
////                                    Window.alert("error");
//                                    dogovorExist.setText("error");
//                                }
//
//                                @Override
//                                public void onSuccess(Boolean b) {
//                                    if (b) {
////                                        Window.alert("yes b="+b);
//                                        dogovorExist.setText("yes");
//                                        textBoxNomerDogovora.setStyleName("error");
//                                    }
//                                    else {
////                                        Window.alert("no b="+b);
//                                        dogovorExist.setText("no");
//                                        textBoxNomerDogovora.setStyleName("noerror");
//                                    }
//                                }
//                            });
//                }else{
//                    textBoxNomerDogovora.setStyleName("error");
//                }
//            }
//        });

        Label label_8 = new Label("Дата заключения");
        nomerData.add(label_8);
        label_8.setSize("137px", "24px");

        dateBoxDataZakluchenDogovora = new DateBox();
        dateBoxDataZakluchenDogovora.setFormat(new DateBox.DefaultFormat(dateFormat));
        nomerData.add(dateBoxDataZakluchenDogovora);
        dateBoxDataZakluchenDogovora.setSize("84px", "16px");
        dateBoxDataZakluchenDogovora.setEnabled(false);

        container.add(nomerData);

        Label label_9 = new Label("СТРАХОВАТЕЛЬ");
        label_9.setStyleName("gwt-Label-big");
        container.add(label_9);
        label_9.setSize("228px", "24px");

        HorizontalPanel fio = new HorizontalPanel();

        Button buttonVibratb = new Button("Выбрать");
        fio.add(buttonVibratb);
        buttonVibratb.setSize("75px", "28px");

        Label label_10 = new Label("ФИО");
        fio.add(label_10);
        label_10.setSize("47px", "24px");

        textBoxFIO = new TextBox();
        fio.add(textBoxFIO);
        textBoxFIO.setSize("559px", "20px");
        textBoxFIO.setReadOnly(true);

        Button buttonChange = new Button("Изменить");
        buttonChange.setText("Изменить");
        fio.add(buttonChange);
        buttonChange.setSize("84px", "28px");

        container.add(fio);
        HorizontalPanel dataPass = new HorizontalPanel();

        Label label_11 = new Label("Дата рождения");
        dataPass.add(label_11);
        label_11.setSize("137px", "24px");

        dateBoxDataRozhdeniya = new DateBox();
        dateBoxDataRozhdeniya.setFormat(new DateBox.DefaultFormat(dateFormat));
        dataPass.add(dateBoxDataRozhdeniya);
        dateBoxDataRozhdeniya.setSize("84px", "16px");

        Label label_12 = new Label("Паспорт серия");
        dataPass.add(label_12);
        label_12.setSize("121px", "24px");

        textBoxPassportSeriya = new TextBox();
        dataPass.add(textBoxPassportSeriya);
        textBoxPassportSeriya.setSize("105px", "20px");

        Label label_13 = new Label("№");
        dataPass.add(label_13);
        label_13.setSize("30px", "24px");

        textBoxPassportNomer = new TextBox();
        dataPass.add(textBoxPassportNomer);
        textBoxPassportNomer.setSize("183px", "20px");

        container.add(dataPass);

        Label label_14 = new Label("Адрес недвижимости");
        label_14.setStyleName("gwt-Label-big");
        container.add(label_14);
        label_14.setSize("321px", "24px");

        HorizontalPanel countries = new HorizontalPanel();

        listBoxCountries = new ListBox();
        listBoxCountries.addItem("Россия");
        listBoxCountries.addItem("Белоруссия");
        listBoxCountries.addItem("Казахстан");
        countries.add(listBoxCountries);
        listBoxCountries.setSize("166px", "32px");

        textBoxIndex = new TextBox();
        countries.add(textBoxIndex);
        textBoxIndex.setSize("91px", "20px");

        textBoxRespKraiObl = new TextBox();
        countries.add(textBoxRespKraiObl);
        textBoxRespKraiObl.setSize("228px", "20px");

        textBoxRayon = new TextBox();
        countries.add(textBoxRayon);
        textBoxRayon.setSize("281px", "20px");

        container.add(countries);
        HorizontalPanel contriesLabel = new HorizontalPanel();

        Label label_15 = new Label("государство");
        label_15.setStyleName("gwt-Label-mini");
        contriesLabel.add(label_15);
        label_15.setSize("107px", "16px");

        Label label_16 = new Label("индекс");
        label_16.setStyleName("gwt-Label-mini");
        contriesLabel.add(label_16);
        label_16.setSize("75px", "16px");

        Label label_17 = new Label("республика, край, область");
        label_17.setStyleName("gwt-Label-mini");
        contriesLabel.add(label_17);
        label_17.setSize("193px", "16px");

        Label label_18 = new Label("район");
        label_18.setStyleName("gwt-Label-mini");
        contriesLabel.add(label_18);
        label_18.setSize("107px", "16px");

        container.add(contriesLabel);
        HorizontalPanel nas = new HorizontalPanel();

        textBoxNaselPunkt = new TextBox();
        nas.add(textBoxNaselPunkt);
        textBoxNaselPunkt.setSize("155px", "20px");

        textBoxStreet = new TextBox();
        nas.add(textBoxStreet);
        textBoxStreet.setSize("281px", "20px");

        textBoxDom = new TextBox();
        nas.add(textBoxDom);
        textBoxDom.setSize("74px", "20px");

        textBoxKorpus = new TextBox();
        nas.add(textBoxKorpus);
        textBoxKorpus.setSize("74px", "20px");

        textBoxStroenie = new TextBox();
        nas.add(textBoxStroenie);
        textBoxStroenie.setSize("65px", "20px");

        textBoxKvartira = new TextBox();
        nas.add(textBoxKvartira);
        textBoxKvartira.setSize("74px", "20px");

        container.add(nas);
        HorizontalPanel nasLabel = new HorizontalPanel();

        Label label_19 = new Label("населенный пункт");
        label_19.setStyleName("gwt-Label-mini");
        nasLabel.add(label_19);
        label_19.setSize("137px", "16px");

        Label label_20 = new Label("улица");
        label_20.setStyleName("gwt-Label-mini");
        nasLabel.add(label_20);
        label_20.setSize("107px", "16px");

        Label label_21 = new Label("дом");
        label_21.setStyleName("gwt-Label-mini");
        nasLabel.add(label_21);
        label_21.setSize("55px", "16px");

        Label label_22 = new Label("корпус");
        label_22.setStyleName("gwt-Label-mini");
        nasLabel.add(label_22);
        label_22.setSize("68px", "16px");

        Label label_23 = new Label("строение");
        label_23.setStyleName("gwt-Label-mini");
        nasLabel.add(label_23);
        label_23.setSize("68px", "16px");

        Label label_24 = new Label("квартира");
        label_24.setStyleName("gwt-Label-mini");
        nasLabel.add(label_24);
        label_24.setSize("68px", "16px");

        container.add(nasLabel);

        Label label_25 = new Label("КОММЕНТАРИЙ");
        label_25.setStyleName("gwt-Label-big");
        container.add(label_25);
        label_25.setSize("228px", "24px");

        HorizontalPanel comm = new HorizontalPanel();

        Label label_26 = new Label("Комментарий к договору (не печатается на полисе)");
        comm.add(label_26);
        label_26.setSize("137px", "84px");

        textAreaComment = new TextArea();
        comm.add(textAreaComment);
        textAreaComment.setSize("648px", "70px");

        container.add(comm);
        HorizontalPanel butt = new HorizontalPanel();

//        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        final Button saveButton = new Button("Сохранить");
        saveButton.getElement().setId("saveButton");
        butt.add(saveButton);

        final Button closeButton = new Button("К списку договоров");
        closeButton.getElement().setId("closeButton");
        butt.add(closeButton);

        container.add(butt);
//        openDialogBox.setWidget(openAbsolutePanel);

        init();

        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
//                openDialogBox.hide();
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
//                            openDialogBox.hide();
                        }
                    });
                }

                if (dogovorExist.getText().equals("yes")){
//                    greetingService.updateDogovor()
                }
            }
        });
    }

    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static boolean isInteger(String str) {
        try {
            double d = Double.parseDouble(str);
            if (d % 1 == 0) {
                return true;
            }
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    @Override
    public Widget asWidget() {
        return container;
    }


    @Override
    public void add(Widget w) {
        container.add(w);
    }

    @Override
    public void clear() {
        container.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return container.iterator();
    }

    @Override
    public boolean remove(Widget w) {
        return container.remove(w);
    }

}
