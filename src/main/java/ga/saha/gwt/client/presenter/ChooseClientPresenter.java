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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import ga.saha.gwt.client.event.ChooseClientEvent;
import ga.saha.gwt.client.event.ChooseClientEventHandler;
import ga.saha.gwt.client.view.ChooseClientView;
import ga.saha.gwt.client.view.ClientView;
import ga.saha.gwt.shared.Strahovatel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

public class ChooseClientPresenter {

    public interface Display {
        HasClickHandlers chooseBtnHandler();

        SelectionChangeEvent.HasSelectionChangedHandlers setSelectionModelCellTable();

        HasClickHandlers newButtonHandler();

        HasClickHandlers closeButtonHandler();

        HasClickHandlers searchButtonHandler();
//        HasKeyUpHandlers numberKeyUpHandler();

        Widget asWidget();

        ChooseClientView getViewInstance();
    }

    final Display display;
    final SimpleEventBus eventBus;


    public ChooseClientPresenter(Display display, SimpleEventBus eventBus) {
        this.display = display;
        this.eventBus = eventBus;
        init();
    }

    public void init() {

        ChooseClientView d = display.getViewInstance();

        display.chooseBtnHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                eventBus.fireEvent(new ChooseClientEvent());
                d.getDialogVibor().hide();
            }
        });

        d.getDialogVibor().center();
        d.getDataProvider().setList(asList());

        display.closeButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                d.getDialogVibor().hide();
            }
        });

        display.searchButtonHandler().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                String url = "/getstrahovatels?lastname="+d.getTextBoxFamily().getText()
                        +"&firstname="+d.getTextBoxName().getText()
                        +"&firstname2="+d.getTextBoxName2().getText();
                // Send request to server and catch any errors.
//                Window.alert(url);
                RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
                builder.setHeader("Content-Type","multipart/form-data, charset=UTF-8;");
                try {
                    builder.setCallback(new RequestCallback() {
                        @Override
                        public void onResponseReceived(Request request,
                                                       Response response) {
//                            Window.alert("response.getText(): "+response.getText());
                            List<Strahovatel> list = parseJsonSratovatels(response.getText());
                            if (list != null) {
                                d.getDataProvider().setList(list);
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
        });

        display.newButtonHandler().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                DogovorPresenter.strahovatel = new Strahovatel("", "", "");
//                clickClient = "create";
                ((SingleSelectionModel<Strahovatel>) display.setSelectionModelCellTable()).clear();
                new ClientPresenter(new ClientView(), eventBus, "create");
            }
        });

        eventBus.addHandler(ChooseClientEvent.TYPE, new ChooseClientEventHandler() {
            @Override
            public void onChooseClient(ChooseClientEvent event) {
                List<Strahovatel> list = new ArrayList<>();
                list.add(DogovorPresenter.strahovatel);
                d.getDataProvider().setList(list);
                d.getStrahTable().setKeyboardSelectedRow(0);
            }
        });

        display.setSelectionModelCellTable().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                Strahovatel strah = ((SingleSelectionModel<Strahovatel>) display.setSelectionModelCellTable()).getSelectedObject();
                if (!strah.getFullName().equals("  ")) {
                    DogovorPresenter.strahovatel.setIdstrah(strah.getIdstrah());
                    DogovorPresenter.strahovatel.setFirstname(strah.getFirstname());
                    DogovorPresenter.strahovatel.setFirstname2(strah.getFirstname2());
                    DogovorPresenter.strahovatel.setLastname(strah.getLastname());
                    DogovorPresenter.strahovatel.setBirth(strah.getBirth());
                    DogovorPresenter.strahovatel.setPassportseria(strah.getPassportseria());
                    DogovorPresenter.strahovatel.setPassportnumber(strah.getPassportnumber());

                    d.getTextBoxFamily().setText(DogovorPresenter.strahovatel.getLastname());
                    d.getTextBoxName().setText(DogovorPresenter.strahovatel.getFirstname());
                    d.getTextBoxName2().setText(DogovorPresenter.strahovatel.getFirstname2());
                }
            }
        });
    }

    private List<Strahovatel> parseJsonSratovatels(String json) {
        JSONValue value = JSONParser.parseStrict(json);
//        JSONObject srahovatelsObject = value.isObject();
        JSONArray strahovatelArray = value.isArray();

        if (strahovatelArray != null) {
            List<Strahovatel> list = new ArrayList<>();
            for (int i = 0; i <= strahovatelArray.size() - 1; i++) {
                JSONObject strahovatelObj = strahovatelArray.get(i).isObject();
                Long idstrah = Long.parseLong(strahovatelObj.get("idstrah").toString());
                String lastname = strahovatelObj.get("lastname").isString().stringValue();
                String firstname = strahovatelObj.get("firstname").isString().stringValue();
                String firstname2 = strahovatelObj.get("firstname2").isString().stringValue();
                DateTimeFormat dtf = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);
                Date birth = dtf.parseStrict(strahovatelObj.get("birth").isString().stringValue());
                Integer passportseria = Integer.parseInt(strahovatelObj.get("passportseria").toString());
                Integer passportnumber = Integer.parseInt(strahovatelObj.get("passportnumber").toString());

                Strahovatel strahovatel = new Strahovatel(idstrah, lastname, firstname, firstname2, birth, passportseria, passportnumber);
                list.add(strahovatel);
            }
            return list;
        }
        return null;
    }
}
