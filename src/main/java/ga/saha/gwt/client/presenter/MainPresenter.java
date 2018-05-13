package ga.saha.gwt.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ga.saha.gwt.client.event.ListUpdateEvent;
import ga.saha.gwt.client.event.ListUpdateEventHandler;
import ga.saha.gwt.client.view.DogovorView;
import ga.saha.gwt.client.view.MainView;
import ga.saha.gwt.shared.Dogovor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainPresenter {
    private HasWidgets container;

    public interface Display{
        HasClickHandlers setOpenButtonHandler();
        HasClickHandlers setCreateButtonHandler();
        SelectionChangeEvent.HasSelectionChangedHandlers setSelectionModelCellTable();
        Widget asWidget();
        MainView getViewInstance();
    }

    final Display display;
    final SimpleEventBus eventBus;
//    SingleSelectionModel<Dogovor> strahSelectionModel = new SingleSelectionModel<>();

    public MainPresenter(Display display, SimpleEventBus eventBus){
        this.display = display;
        this.eventBus = eventBus;
    }

    public void init(){

        display.setCreateButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
//                existDogovor = new Dogovor();
                new DogovorPresenter(new DogovorView(), eventBus, 0L);
            }
        });

        eventBus.addHandler(ListUpdateEvent.TYPE, new ListUpdateEventHandler(){
            @Override
            public void onListUpdate(ListUpdateEvent chooseClientEvent) {
//                display.getViewInstance().getDataProvider().refresh();
                getDogovorsToDataProvider();
            }
        });


        display.setOpenButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Dogovor dogovor = ((SingleSelectionModel<Dogovor>) display.setSelectionModelCellTable()).getSelectedObject();
                if (dogovor.getIddog().toString().length()>0)
                        new DogovorPresenter(new DogovorView(), eventBus, dogovor.getIddog());
            }
        });

        getDogovorsToDataProvider();
    }

    private void getDogovorsToDataProvider() {
        String url = "/getdogovors";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
        try {
            builder.setCallback(new RequestCallback() {
                @Override
                public void onResponseReceived(Request request,
                                               Response response) {
//                            Window.alert("response.getText(): "+response.getText());
                    List<Dogovor> list = parseJsonDogovors(response.getText());
                    if (list != null) {
                        display.getViewInstance().getDataProvider().setList(list);
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
    }

    private List<Dogovor> parseJsonDogovors(String json) {
        JSONValue value = JSONParser.parseStrict(json);
//        JSONObject srahovatelsObject = value.isObject();
        JSONArray dogovorArray = value.isArray();

        if (dogovorArray != null) {
            List<Dogovor> list = new ArrayList<>();
            for (int i = 0; i <= dogovorArray.size() - 1; i++) {
                JSONObject dogovorlObj = dogovorArray.get(i).isObject();
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

                Dogovor dogovor = new Dogovor(iddog, datazakl, strahovatelid, strsumma, startdate, enddate, type,
                        year, squair, daterasheta, premiya, state, index, krai, district, town, street, home,
                        korpus, stroenie, flat, comment, fullname);
                list.add(dogovor);
            }
            return list;
        }
        return null;
    }

    public void go(final HasWidgets container){
        init();
        this.container = container;
        container.clear();
        container.add(display.asWidget());
    }

}
