package ga.saha.gwt.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import ga.saha.gwt.client.event.ChooseClientEvent;
import ga.saha.gwt.client.view.ClientView;
import ga.saha.gwt.shared.Strahovatel;

import java.util.Date;

import static ga.saha.gwt.client.presenter.DogovorPresenter.strahovatel;

public class ClientPresenter {

    public interface Display {
        HasClickHandlers getCloseButtonHandler();

        HasClickHandlers getSaveButtonHandler();

        Widget asWidget();

        ClientView getViewInstance();
    }

    final Display display;
    final SimpleEventBus eventBus;
    final String sender;

    public ClientPresenter(Display display, SimpleEventBus eventBus, String sender) {
        this.display = display;
        this.eventBus = eventBus;
        this.sender = sender;
        init();
    }

    public void init() {

        ClientView d = display.getViewInstance();

        d.getDialogClient().center();

//        Window.alert(strahovatel.toString());

        try {
            d.getTextBoxFamily1().setText(strahovatel.getLastname());
            d.getTextBoxName1().setText(strahovatel.getFirstname());
            d.getTextBoxName21().setText(strahovatel.getFirstname2());
            d.getDateBoxDataRozhdeniya2().setValue(strahovatel.getBirth());
            d.getTextBoxPassportSeriya2().setText(strahovatel.getPassportseria().toString());
            d.getTextBoxPassportNomer2().setText(strahovatel.getPassportnumber().toString());
        } catch (Exception ignored) {
        }

        display.getCloseButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                d.getDialogClient().hide();
            }
        });

        display.getSaveButtonHandler().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                String url = "/savestrahovatel";
                if (sender.equals("update")) {
                    url = "/updatestrahovatel/" + strahovatel.getIdstrah();
                }
                String strahstring = "{\"lastname\":\"" + d.getTextBoxFamily1().getText() + "\""
//                        + ", \"idstrah\":" + strahovatel.getIdstrah()
                        + ", \"firstname\":\"" + d.getTextBoxName1().getText() + "\""
                        + ", \"firstname2\":\"" + d.getTextBoxName21().getText() + "\""
                        + ", \"birth\":" + d.getDateBoxDataRozhdeniya2().getValue().getTime()
                        + ", \"passportseria\":" + Integer.valueOf(d.getTextBoxPassportSeriya2().getText())
                        + ", \"passportnumber\":" + Integer.valueOf(d.getTextBoxPassportNomer2().getText()) + "}";

//                    JSONArray postdata=new JSONArray();
                RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
                builder.setHeader("Accept", "application/json");
                builder.setHeader("Content-type", "application/json");
                builder.setRequestData(strahstring);
                try {
                    builder.setCallback(new RequestCallback() {
                        @Override
                        public void onResponseReceived(Request request, Response response) {
//                                Window.alert("response.getText(): "+response.getText());
                            strahovatel = responseToStrahovatel(response);
//                                Window.alert("strahovatel: "+strahovatel);
                            eventBus.fireEvent(new ChooseClientEvent());
                            d.getDialogClient().hide();
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
    }

    private Strahovatel responseToStrahovatel(Response response) {
        JSONValue value = JSONParser.parseStrict(response.getText());
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
