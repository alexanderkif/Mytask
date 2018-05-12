package ga.saha.gwt.client.presenter;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import ga.saha.gwt.client.event.ChooseClientEvent;
import ga.saha.gwt.client.event.ChooseClientEventHandler;
import ga.saha.gwt.client.event.ListUpdateEvent;
import ga.saha.gwt.client.view.ChooseClientView;
import ga.saha.gwt.client.view.ClientView;
import ga.saha.gwt.client.view.DogovorView;
import ga.saha.gwt.shared.Dogovor;
import ga.saha.gwt.shared.Strahovatel;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;


public class DogovorPresenter {

    private static List<String> listNedvizhimosti = asList("Квартира","Дом","Комната");
    private static List<String> countries = asList("Россия","Белоруссия","Казахстан");

    protected static Strahovatel strahovatel = new Strahovatel("","","");

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
    Dogovor existDogovor;
    Long iddog;


    public DogovorPresenter(Display display, SimpleEventBus eventBus, Long iddog) {
        this.display = display;
        this.eventBus = eventBus;
        this.iddog = iddog;
        init();
    }

    public void init() {

        DogovorView d = display.getViewInstance();

        d.getOpenDialogBox().center();

        if (iddog!=0L) {
            String url = "/getdogovor/" + iddog;
            RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
            try {
                builder.setCallback(new RequestCallback() {
                    @Override
                    public void onResponseReceived(Request request, Response response) {
//                        Window.alert("response.getText(): "+response.getText());
                        if (!Objects.equals(response.getText(), "")) {
                            existDogovor = parseJsonToDogovor(response.getText());
//                            Window.alert("existDogovor: "+existDogovor);
                            d.getStrSumma().setText(existDogovor.getStrsumma().toString());
                            d.getTextBoxGodPostroiki().setText(existDogovor.getYear());
                            d.getTextBoxPloshadb().setText(existDogovor.getSquair());
                            d.getComboBoxTipNedvizhimosti().clear();
                            d.getComboBoxTipNedvizhimosti().addItem(existDogovor.getType());
                            for (String s : listNedvizhimosti) {
                                d.getComboBoxTipNedvizhimosti().addItem(s);
                            }
                            d.getDateBoxEnd().setValue(existDogovor.getEnddate());
                            d.getTextBoxPremiya().setText(existDogovor.getPremiya());
                            d.getDateBoxDataRascheta().setValue(existDogovor.getDaterasheta());
                            d.getTextBoxNomerDogovora().setText(existDogovor.getIddog().toString());
                            d.getDateBoxDataZakluchenDogovora().setValue(existDogovor.getDatazakl());
                            d.getDateBoxStart().setValue(existDogovor.getStartdate());

                            String url1 = "/getstrahovatel/" + existDogovor.getStrahovatelid();
                            RequestBuilder builder1 = new RequestBuilder(RequestBuilder.GET, url1);
                            try {
                                builder1.setCallback(new RequestCallback() {
                                    @Override
                                    public void onResponseReceived(Request request, Response response) {
//                            Window.alert("response.getText(): "+response.getText());
                                        if (response != null) {
                                            strahovatel = parseJsonToSratovatel(response.getText());
                                            d.getTextBoxFIO().setText(strahovatel.getFullName());
                                            d.getDateBoxDataRozhdeniya().setValue(strahovatel.getBirth());
                                            d.getTextBoxPassportSeriya().setText(String.valueOf(strahovatel.getPassportseria()));
                                            d.getTextBoxPassportNomer().setText(String.valueOf(strahovatel.getPassportnumber()));
                                        }else{
                                            d.getTextBoxFIO().setText("Страхователь не найден.");
                                        }
                                    }
                                    @Override
                                    public void onError(Request request, Throwable exception) {
                                        Window.alert(exception.getMessage());
                                    }
                                });
                                builder1.send();
                            } catch (RequestException ex) {
                                Window.alert(ex.getMessage());
                            }
//        listBoxCountries.addItem(existDogovor.getAddressOb().getState());
                            d.getListBoxCountries().clear();
                            d.getListBoxCountries().addItem(existDogovor.getState());
                            for (String s : countries) {
                                d.getListBoxCountries().addItem(s);
                            }
                            d.getTextBoxIndex().setText(existDogovor.getIndex());
                            d.getTextBoxRespKraiObl().setText(existDogovor.getKrai());
                            d.getTextBoxRayon().setText(existDogovor.getDistrict());
                            d.getTextBoxNaselPunkt().setText(existDogovor.getTown());
                            d.getTextBoxStreet().setText(existDogovor.getStreet());
                            d.getTextBoxDom().setText(existDogovor.getHome().toString());
                            d.getTextBoxKorpus().setText(existDogovor.getKorpus());
                            d.getTextBoxStroenie().setText(existDogovor.getStroenie());
                            d.getTextBoxKvartira().setText(existDogovor.getFlat().toString());
                            d.getTextAreaComment().setText(existDogovor.getComment());
                            d.getTextBoxNomerDogovora().setReadOnly(true);
                        }else{
                            Window.alert("Договор не найден.");
                        }
                    }
                    @Override
                    public void onError(Request request, Throwable exception) {
                        Window.alert(exception.getMessage());
                    }
                });
                builder.send();
            } catch (RequestException ex) {
                Window.alert(ex.getMessage());
            }
        } else {
            existDogovor = new Dogovor();
            strahovatel = new Strahovatel();
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
                d.getTextBoxPassportSeriya().setText(strahovatel.getPassportseria().toString());
                d.getTextBoxPassportNomer().setText(strahovatel.getPassportnumber().toString());
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
//                    clickClient = "update";
                    new ClientPresenter(new ClientView(), eventBus, "update");
                }
            }
        });

        display.saveButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                String url = "/savedogovor";
                if (iddog!=0L) {
                    url = "/updatedogovor/" + Long.valueOf(d.getTextBoxNomerDogovora().getText());
                }
                String strahstring = "{\"strahovatelid\":" + strahovatel.getIdstrah()
                        + ", \"datazakl\":" + d.getDateBoxDataZakluchenDogovora().getValue().getTime()
                        + ", \"startdate\":" + d.getDateBoxStart().getValue().getTime()
                        + ", \"enddate\":" + d.getDateBoxEnd().getValue().getTime()
                        + ", \"daterasheta\":" + d.getDateBoxDataRascheta().getValue().getTime()
                        + ", \"strsumma\":" + Integer.valueOf(d.getStrSumma().getText())
                        + ", \"home\":" + Integer.valueOf(d.getTextBoxDom().getText())
                        + ", \"flat\":" + Integer.valueOf(d.getTextBoxKvartira().getText())
                        + ", \"type\":\"" + d.getComboBoxTipNedvizhimosti().getSelectedItemText() + "\""
                        + ", \"year\":\"" + d.getTextBoxGodPostroiki().getText() + "\""
                        + ", \"squair\":\"" + d.getTextBoxPloshadb().getText() + "\""
                        + ", \"premiya\":\"" + d.getTextBoxPremiya().getText() + "\""
                        + ", \"state\":\"" + d.getListBoxCountries().getSelectedItemText() + "\""
                        + ", \"index\":\"" + d.getTextBoxIndex().getText() + "\""
                        + ", \"krai\":\"" + d.getTextBoxRespKraiObl().getText() + "\""
                        + ", \"district\":\"" + d.getTextBoxRayon().getText() + "\""
                        + ", \"town\":\"" + d.getTextBoxNaselPunkt().getText() + "\""
                        + ", \"street\":\"" + d.getTextBoxStreet().getText() + "\""
                        + ", \"korpus\":\"" + d.getTextBoxKorpus().getText() + "\""
                        + ", \"stroenie\":\"" + d.getTextBoxStroenie().getText() + "\""
                        + ", \"comment\":\"" + d.getTextAreaComment().getText() + "\""
                        + ", \"fullname\":\"" + d.getTextBoxFIO().getText() + "\"}";

//                Window.alert("url: "+url+"    strahstring: "+strahstring);
//                    JSONArray postdata=new JSONArray();
                RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
                builder.setHeader("Accept", "application/json");
                builder.setHeader("Content-type", "application/json");
                builder.setRequestData(strahstring);
                try {
                    builder.setCallback(new RequestCallback() {
                        @Override
                        public void onResponseReceived(Request request, Response response) {
                            eventBus.fireEvent(new ListUpdateEvent());
                            d.getOpenDialogBox().hide();
                        }

                        @Override
                        public void onError(Request request, Throwable exception) {
                            Window.alert(exception.getMessage());
                        }
                    });
                    builder.send();
                } catch (RequestException ex) {
                    Window.alert(ex.getMessage());
                }
            }
        });

        display.numberKeyUpHandler().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                if (Integer.valueOf(d.getTextBoxNomerDogovora().getText()).toString().length() == 6) {
                    String url = "/getdogovor/" + Long.valueOf(d.getTextBoxNomerDogovora().getText());
                    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
                    try {
                        builder.setCallback(new RequestCallback() {
                            @Override
                            public void onResponseReceived(Request request, Response response) {
                                if (response.getText().equals("")){
                                    d.getTextBoxNomerDogovora().setStyleName("noerror");
                                }else{
                                    d.getTextBoxNomerDogovora().setStyleName("error");
                                }
                            }

                            @Override
                            public void onError(Request request, Throwable exception) {
                                Window.alert(exception.getMessage());
                            }
                        });
                        builder.send();
                    } catch (RequestException ex) {
                        Window.alert(ex.getMessage());
                    }
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

                List<String> list = existDogovor.raschet(d.getStrSumma().getText(), d.getComboBoxTipNedvizhimosti().getSelectedItemText(), d.getTextBoxGodPostroiki().getText(), d.getTextBoxPloshadb().getText(), d.getDateBoxStart().getValue(), d.getDateBoxEnd().getValue());

                d.getError().setText(list.get(0));

                if (list.get(0).equals("")) {
                    d.getDateBoxDataRascheta().setValue(today);
                    d.getTextBoxPremiya().setText(list.get(1));
                }
            }
        });

    }

    private Dogovor parseJsonToDogovor(String json) {
        JSONValue value = JSONParser.parseStrict(json);
        JSONObject dogovorlObj = value.isObject();
        Long iddog = Long.parseLong(dogovorlObj.get("iddog").toString());
        Long strahovatelid = Long.parseLong(dogovorlObj.get("strahovatelid").toString());
        DateTimeFormat dtf = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);
        Date datazakl = dtf.parseStrict(dogovorlObj.get("datazakl").isString().stringValue());
        Date startdate = dtf.parseStrict(dogovorlObj.get("startdate").isString().stringValue());
        Date enddate = dtf.parseStrict(dogovorlObj.get("enddate").isString().stringValue());
        Date daterasheta = dtf.parseStrict(dogovorlObj.get("daterasheta").isString().stringValue());
        Integer strsumma = Integer.parseInt(dogovorlObj.get("strsumma").toString());
        Integer home = Integer.parseInt(dogovorlObj.get("home").toString());
        Integer flat = Integer.parseInt(dogovorlObj.get("flat").toString());
        String type = dogovorlObj.get("type").isString().stringValue();
        String year = dogovorlObj.get("year").isString().stringValue();
        String squair = dogovorlObj.get("squair").isString().stringValue();
        String premiya = dogovorlObj.get("premiya").isString().stringValue();
        String state = dogovorlObj.get("state").isString().stringValue();
        String index = dogovorlObj.get("index").isString().stringValue();
        String krai = dogovorlObj.get("krai").isString().stringValue();
        String district = dogovorlObj.get("district").isString().stringValue();
        String town = dogovorlObj.get("town").isString().stringValue();
        String street = dogovorlObj.get("street").isString().stringValue();
        String korpus = dogovorlObj.get("korpus").isString().stringValue();
        String stroenie = dogovorlObj.get("stroenie").isString().stringValue();
        String comment = dogovorlObj.get("comment").isString().stringValue();
        String fullname = dogovorlObj.get("fullname").isString().stringValue();

        return new Dogovor(iddog, datazakl, strahovatelid, strsumma, startdate, enddate, type,
                year, squair, daterasheta, premiya, state, index, krai, district, town, street, home,
                korpus, stroenie, flat, comment, fullname);
    }

    private Strahovatel parseJsonToSratovatel(String json) {
        JSONValue value = JSONParser.parseStrict(json);
        JSONObject strahovatelObj = value.isObject();
        Long idstrah = Long.parseLong(strahovatelObj.get("idstrah").toString());
        String lastname = strahovatelObj.get("lastname").isString().stringValue();
        String firstname = strahovatelObj.get("firstname").isString().stringValue();
        String firstname2 = strahovatelObj.get("firstname2").isString().stringValue();
        DateTimeFormat dtf = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);
        Date birth = dtf.parseStrict(strahovatelObj.get("birth").isString().stringValue());
        Integer passportseria = Integer.parseInt(strahovatelObj.get("passportseria").toString());
        Integer passportnumber = Integer.parseInt(strahovatelObj.get("passportnumber").toString());
        return new Strahovatel(idstrah, lastname, firstname, firstname2, birth, passportseria, passportnumber);
    }

}
