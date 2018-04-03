package ga.skif.task.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import ga.skif.task.client.entity.AddressOb;
import ga.skif.task.client.entity.Dogovor;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static ga.skif.task.client.Mytask.*;

public class DogovorPresenter {

    private String dogovorInBase;

    public interface Display {
        HasClickHandlers closeButtonHandler();
        HasClickHandlers raschetButtonHandler();
        HasClickHandlers saveButtonHandler();
        HasKeyUpHandlers numberKeyUpHandler();

        Widget asWidget();
        DogovorView getViewInstance();
    }

    final Display display;
    final HandlerManager eventBus;
    final DateTimeFormat yearFormat = DateTimeFormat.getFormat("yyyy");
    final Date today = new Date();


    public DogovorPresenter(Display display, HandlerManager eventBus) {
        this.display = display;
        this.eventBus = eventBus;
//        Window.alert("DogovorPresenter "+existDogovor.toString());
        init();
        if (existDogovor.getId().toString().length() > 0) {
            display.getViewInstance().getTextBoxNomerDogovora().setReadOnly(true);
            display.getViewInstance().getDateBoxDataZakluchenDogovora().setValue(existDogovor.getDataZakl());
        } else {
            display.getViewInstance().getTextBoxNomerDogovora().setReadOnly(false);
            display.getViewInstance().getDateBoxDataZakluchenDogovora().setValue(today);
        }
    }

    public void init() {

        DogovorView d = display.getViewInstance();

//        display.closeButtonHandler().addClickHandler(new ClickHandler() {
        d.getCloseButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                d.getOpenDialogBox().hide();
            }
        });

//        display.saveButtonHandler().addClickHandler(new ClickHandler() {
        d.getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                AddressOb addressOb = new AddressOb(d.getListBoxCountries().getSelectedItemText(), d.getTextBoxIndex().getText(),
                        d.getTextBoxRespKraiObl().getText(), d.getTextBoxRayon().getText(), d.getTextBoxNaselPunkt().getText(),
                        d.getTextBoxStreet().getText(), Integer.valueOf(d.getTextBoxDom().getText()), d.getTextBoxKorpus().getText(),
                        d.getTextBoxStroenie().getText(), Integer.valueOf(d.getTextBoxKvartira().getText()),
                        d.getTextAreaComment().getText());
                Dogovor dogovor = new Dogovor();
                dogovor.setId(Integer.valueOf(d.getTextBoxNomerDogovora().getText()));
                dogovor.setDataZakl(d.getDateBoxDataZakluchenDogovora().getValue());
                dogovor.setStrahovatel(strahovatel);
                dogovor.setAddressOb(addressOb);
                dogovor.setStart(d.getDateBoxStart().getValue());
                dogovor.setEnd(d.getDateBoxEnd().getValue());
                dogovor.setStrSumma(Integer.valueOf(d.getStrSumma().getText()));
                dogovor.setType(d.getComboBoxTipNedvizhimosti().getSelectedItemText());
                dogovor.setYear(d.getTextBoxGodPostroiki().getText());
                dogovor.setSquair(d.getTextBoxPloshadb().getText());
                dogovor.setPremiya(d.getTextBoxPremiya().getText());
                dogovor.setDateRasheta(d.getDateBoxDataRascheta().getValue());

                if (dogovorInBase.equals("no")) {
                    greetingService.createDogovor(dogovor, new AsyncCallback<Boolean>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            Window.alert("Не сохранено");
                        }

                        @Override
                        public void onSuccess(Boolean aBoolean) {
//                            Window.alert("Cохранено");
                            Mytask.list.add(dogovor);
                            d.getOpenDialogBox().hide();
                        }
                    });
                }

                if (dogovorInBase.equals("yes")) {
//                    greetingService.updateDogovor()
                }
            }
        });

//        display.numberKeyUpHandler().addKeyUpHandler(new KeyUpHandler() {
        d.getTextBoxNomerDogovora().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                if (d.getTextBoxNomerDogovora().getText().length() == 6) {
                    greetingService.checkDogId(Integer.valueOf(d.getTextBoxNomerDogovora().getText()),
                            new AsyncCallback<Boolean>() {

                                @Override
                                public void onFailure(Throwable throwable) {
//                                    Window.alert("error");
                                    dogovorInBase = "error";
                                }

                                @Override
                                public void onSuccess(Boolean b) {
                                    if (b) {
//                                        Window.alert("yes b="+b);
                                        dogovorInBase = "yes";
                                        d.getTextBoxNomerDogovora().setStyleName("error");
                                    } else {
//                                        Window.alert("no b="+b);
                                        dogovorInBase = "no";
                                        d.getTextBoxNomerDogovora().setStyleName("noerror");
                                    }
                                }
                            });
                } else {
                    d.getTextBoxNomerDogovora().setStyleName("error");
                }
            }
        });

//        display.raschetButtonHandler().addClickHandler(new ClickHandler() {
        d.getBtnRasschitat().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                Integer strSum = 0;
                Double koTH = 0.0, koGP = 0.0, koPL = 0.0;
                String errString = "";

                if (!isInteger(d.getStrSumma().getText())) {
                    errString += "Страховая сумма должна быть целым числом. ";
                } else {
                    strSum = Integer.valueOf(d.getStrSumma().getText());
                }

                if (Objects.equals(d.getComboBoxTipNedvizhimosti().getSelectedItemText(), "")) {
                    errString += "Тип недвижимости должен быть выбран. ";
                } else {
                    switch (d.getComboBoxTipNedvizhimosti().getSelectedItemText()) {
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

                if (!isInteger(d.getTextBoxGodPostroiki().getText()) || d.getTextBoxGodPostroiki().getText().length() != 4) {
                    errString += "Введите год постройки (4 цифры). ";
                } else {
                    if (Integer.valueOf(d.getTextBoxGodPostroiki().getText()) > Integer.valueOf(yearFormat.format(today))) {
                        errString += "Дом еще не построен. ";
                    } else {
                        if (Integer.valueOf(d.getTextBoxGodPostroiki().getText()) < 2000) {
                            koGP = 1.3;
                        } else {
                            if (Integer.valueOf(d.getTextBoxGodPostroiki().getText()) < 2014) {
                                koGP = 1.6;
                            } else {
                                koGP = 2.0;
                            }
                        }
                    }
                }

                if (!isNumeric(d.getTextBoxPloshadb().getText())) {
                    errString += "Введите правильно площадь. ";
                } else {
                    if (Double.valueOf(d.getTextBoxPloshadb().getText()) < 50) {
                        koPL = 1.2;
                    } else {
                        if (Double.valueOf(d.getTextBoxPloshadb().getText()) < 100) {
                            koPL = 1.5;
                        } else {
                            koPL = 2.0;
                        }
                    }
                }

                if (d.getDateBoxStart().getValue().getTime() - today.getTime() < -1000 * 60 * 60 * 24) {
                    errString += "Дата начала договора не может быть меньше текущей даты. ";
                }

                if (d.getDateBoxEnd().getValue().getTime() - d.getDateBoxStart().getValue().getTime() < 0) {
                    errString += "Дата окончания договора не может быть меньше даты начала. ";
                }

                if (TimeUnit.DAYS.convert(
                        d.getDateBoxEnd().getValue().getTime() - d.getDateBoxStart().getValue().getTime(),
                        TimeUnit.MILLISECONDS) > 365) {
                    errString += "Договор не может действовать дольше года. ";
                }

                d.getError().setText(errString);

                if (errString.equals("")) {
                    d.getDateBoxDataRascheta().setValue(today);
                    //Страховая премия = (Страховая сумма / кол-во дней) * Коэф.ТН * Коэф.ГП * Коэф.Пл
                    double prem = ((int) (strSum * koTH * koGP * koPL * 100 / TimeUnit.DAYS.convert(
                            d.getDateBoxEnd().getValue().getTime() - d.getDateBoxStart().getValue().getTime(),
                            TimeUnit.MILLISECONDS))) / 100.0;
                    d.getTextBoxPremiya().setText(String.valueOf(prem));
                }
            }
        });

        d.getStrSumma().setText(existDogovor.getStrSumma().toString());
        d.getTextBoxGodPostroiki().setText(existDogovor.getYear());
        d.getTextBoxPloshadb().setText(existDogovor.getSquair());
        d.getComboBoxTipNedvizhimosti().clear();
        d.getComboBoxTipNedvizhimosti().addItem(existDogovor.getType());
        for (String s : listNedvizhimosti) {
            d.getComboBoxTipNedvizhimosti().addItem(s);
        }
        d.getDateBoxStart().setValue(existDogovor.getStart());
        d.getDateBoxEnd().setValue(existDogovor.getEnd());
        d.getTextBoxPremiya().setText(existDogovor.getPremiya());
        d.getDateBoxDataRascheta().setValue(existDogovor.getDateRasheta());
        d.getTextBoxNomerDogovora().setText(existDogovor.getId().toString());
//        d.getDateBoxDataZakluchenDogovora().setValue(existDogovor.getDataZakl());
        d.getTextBoxFIO().setText(existDogovor.getStrahovatel().getFullName());
        d.getDateBoxDataRozhdeniya().setValue(existDogovor.getStrahovatel().getBirth());
        d.getTextBoxPassportSeriya().setText(String.valueOf(existDogovor.getStrahovatel().getPassportSeria()));
        d.getTextBoxPassportNomer().setText(String.valueOf(existDogovor.getStrahovatel().getPassportNumber()));
//        listBoxCountries.addItem(existDogovor.getAddressOb().getState());
        d.getListBoxCountries().clear();
        d.getListBoxCountries().addItem(existDogovor.getAddressOb().getState());
        for (String s : countries) {
            d.getListBoxCountries().addItem(s);
        }
        d.getTextBoxIndex().setText(existDogovor.getAddressOb().getIndex());
        d.getTextBoxRespKraiObl().setText(existDogovor.getAddressOb().getKrai());
        d.getTextBoxRayon().setText(existDogovor.getAddressOb().getDistrict());
        d.getTextBoxNaselPunkt().setText(existDogovor.getAddressOb().getTown());
        d.getTextBoxStreet().setText(existDogovor.getAddressOb().getStreet());
        d.getTextBoxDom().setText(existDogovor.getAddressOb().getHome().toString());
        d.getTextBoxKorpus().setText(existDogovor.getAddressOb().getKorpus());
        d.getTextBoxStroenie().setText(existDogovor.getAddressOb().getStroenie());
        d.getTextBoxKvartira().setText(existDogovor.getAddressOb().getFlat().toString());
        d.getTextAreaComment().setText(existDogovor.getAddressOb().getComment());
        strahovatel = existDogovor.getStrahovatel();

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

    public void go(final HasWidgets container) {
        init();
//        container.clear();
//        container.add(display.asWidget());

    }

    public Display getView() {
        return display;
    }
}
