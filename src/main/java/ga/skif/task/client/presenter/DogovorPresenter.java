package ga.skif.task.client.presenter;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.impl.Validation;
import ga.skif.task.client.event.ListUpdateEvent;
import ga.skif.task.client.view.ClientView;
import ga.skif.task.client.view.ChooseClientView;
import ga.skif.task.client.view.DogovorView;
import ga.skif.task.client.Mytask;
import ga.skif.task.client.entity.AddressOb;
import ga.skif.task.client.entity.Dogovor;
import ga.skif.task.client.event.ChooseClientEvent;
import ga.skif.task.client.event.ChooseClientEventHandler;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static ga.skif.task.client.Mytask.*;

public class DogovorPresenter {

    private String dogovorInBase;

    public interface Display {
        HasClickHandlers closeButtonHandler();
        HasClickHandlers raschetButtonHandler();
        HasClickHandlers saveButtonHandler();
        HasKeyUpHandlers numberKeyUpHandler();
        HasClickHandlers chooseClientHandler();
        HasClickHandlers changeClientHandler();
        Widget asWidget();
        DogovorView getViewInstance();
    }

    final Display display;
    final SimpleEventBus eventBus;
    final Date today = new Date();


    public DogovorPresenter(Display display, SimpleEventBus eventBus) {
        this.display = display;
        this.eventBus = eventBus;
        init();
    }

    public void init() {

        DogovorView d = display.getViewInstance();

        d.getOpenDialogBox().center();

//        Window.alert(strahovatel.toString());
//        Window.alert(existDogovor.toString());


        if (clickDogovor.equals("open")) {
            d.getStrSumma().setText(existDogovor.getStrSumma().toString());
            d.getTextBoxGodPostroiki().setText(existDogovor.getYear());
            d.getTextBoxPloshadb().setText(existDogovor.getSquair());
            d.getComboBoxTipNedvizhimosti().clear();
            d.getComboBoxTipNedvizhimosti().addItem(existDogovor.getType());
            for (String s : listNedvizhimosti) {
                d.getComboBoxTipNedvizhimosti().addItem(s);
            }
            d.getDateBoxEnd().setValue(existDogovor.getEnd());
            d.getTextBoxPremiya().setText(existDogovor.getPremiya());
            d.getDateBoxDataRascheta().setValue(existDogovor.getDateRasheta());
            d.getTextBoxNomerDogovora().setText(existDogovor.getId().toString());
            d.getDateBoxDataZakluchenDogovora().setValue(existDogovor.getDataZakl());
            d.getDateBoxStart().setValue(existDogovor.getStart());
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
            d.getTextBoxNomerDogovora().setReadOnly(true);
        }else{
            d.getTextBoxNomerDogovora().setReadOnly(false);
            d.getDateBoxDataRascheta().setValue(today);
            d.getDateBoxStart().setValue(today);
            d.getDateBoxDataZakluchenDogovora().setValue(today);
        }

        eventBus.addHandler(ChooseClientEvent.TYPE, new ChooseClientEventHandler() {
            @Override
            public void onChooseClient(ChooseClientEvent event) {
                d.getTextBoxFIO().setText(strahovatel.getFullName());
                d.getDateBoxDataRozhdeniya().setValue(strahovatel.getBirth());
                d.getTextBoxPassportSeriya().setText(strahovatel.getPassportSeria().toString());
                d.getTextBoxPassportNomer().setText(strahovatel.getPassportNumber().toString());
//               Window.alert("ChooseClientEvent.TYPE");
            }
        });

        display.closeButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                d.getOpenDialogBox().hide();
            }
        });

        display.chooseClientHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                new ChooseClientPresenter(new ChooseClientView(), eventBus);
            }
        });

        display.changeClientHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (!strahovatel.getFullName().equals("  ")) {
                    clickClient = "update";
                    new ClientPresenter(new ClientView(), eventBus);
                }
            }
        });

        display.saveButtonHandler().addClickHandler(new ClickHandler() {
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
//                Window.alert("dogovor.getId() "+dogovor.getId());
                greetingService.checkDogId(dogovor.getId(), new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable throwable) {}
                    @Override
                    public void onSuccess(Boolean b) {
                        if (b) {
                            greetingService.updateDogovor(dogovor, new AsyncCallback<Boolean>() {
                                @Override
                                public void onFailure(Throwable throwable) {
                                    Window.alert("Не сохранено");
                                }

                                @Override
                                public void onSuccess(Boolean aBoolean) {
//                                    Window.alert("updateDogovor Success");
                                    Mytask.list.remove(dogovor);
                                    Mytask.list.add(dogovor);
                                    d.getOpenDialogBox().hide();
                                    eventBus.fireEvent(new ListUpdateEvent());
                                }
                            });
                        } else {
                            greetingService.createDogovor(dogovor, new AsyncCallback<Boolean>() {
                                @Override
                                public void onFailure(Throwable throwable) {
                                    Window.alert("Не сохранено");
                                }

                                @Override
                                public void onSuccess(Boolean aBoolean) {
                                    Mytask.list.add(dogovor);
                                    d.getOpenDialogBox().hide();
                                    eventBus.fireEvent(new ListUpdateEvent());
                                }
                            });
                        }
                    }
                });
            }
        });

        display.numberKeyUpHandler().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                if (Integer.valueOf(d.getTextBoxNomerDogovora().getText()).toString().length() == 6) {
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

        display.raschetButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

//                Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//                Set<ConstraintViolation<Dogovor>> violations = validator.validate(existDogovor, Dogovor.Minimal.class);

                List<String> list = existDogovor.raschet(d.getStrSumma().getText(),
                        d.getComboBoxTipNedvizhimosti().getSelectedItemText(),
                        d.getTextBoxGodPostroiki().getText(),
                        d.getTextBoxPloshadb().getText(),
                        d.getDateBoxStart().getValue(),
                        d.getDateBoxEnd().getValue()
                        );

                d.getError().setText(list.get(0));

                if (list.get(0).equals("")) {
                    d.getDateBoxDataRascheta().setValue(today);
                    d.getTextBoxPremiya().setText(list.get(1));
                }
            }
        });

    }

}
