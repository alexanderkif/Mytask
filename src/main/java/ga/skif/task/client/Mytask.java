package ga.skif.task.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.RootPanel;
import ga.skif.task.client.entity.Dogovor;
import ga.skif.task.client.entity.Strahovatel;
import ga.skif.task.client.service.GreetingService;
import ga.skif.task.client.service.GreetingServiceAsync;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Mytask implements EntryPoint {

    public static Strahovatel strahovatel = new Strahovatel("","","");

    public static Dogovor existDogovor = new Dogovor();

    public static String clickClient = "";

    public static String clickDogovor = "";

    public static List<String> listNedvizhimosti = asList("Квартира","Дом","Комната");

    public static List<String> countries = asList("Россия","Белоруссия","Казахстан");

    public static GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    public static DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");

    public static List<Dogovor> list = new ArrayList<>();

    private CellTable<Dogovor> cellTable = new CellTable<>();
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

//        RootPanel rootPanel = RootPanel.get("vp");

        SimpleEventBus eventBus = new SimpleEventBus();
        AppController app = new AppController(eventBus);
        app.goTo(RootPanel.get("vp"));
//        MainPresenter main = new MainPresenter(new MainView(), eventBus);
//        main.go(RootPanel.get());

    }
}
