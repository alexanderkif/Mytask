package ga.skif.task.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Mytask implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    RootPanel rootPanel = RootPanel.get("vp");

    final Button txtbtnCreate = new Button("Создать договор");
    rootPanel.add(txtbtnCreate, 130, 20);

    final Button txtbtnOpen = new Button("Открыть договор");
    rootPanel.add(txtbtnOpen, 270, 20);

    final CellTable<Dogovor> cellTable = new CellTable();
    rootPanel.add(cellTable, 130, 60);

    TextColumn<Dogovor> idColumn = new TextColumn<Dogovor>() {
      @Override
      public String getValue(Dogovor dogovor) {
        return dogovor.getId().toString();
      }
    };
    cellTable.addColumn(idColumn,"Серия-Номер");
    TextColumn<Dogovor> dateZaklColumn = new TextColumn<Dogovor>() {
      @Override
      public String getValue(Dogovor dogovor) {
        return DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(dogovor.getDataZakl());
      }
    };
    cellTable.addColumn(dateZaklColumn,"Дата заключения");
    TextColumn<Dogovor> strahovatelColumn = new TextColumn<Dogovor>() {
      @Override
      public String getValue(Dogovor dogovor) {
        return dogovor.getStrahovatel().getFullName();
      }
    };
    cellTable.addColumn(strahovatelColumn,"Страхователь");
    TextColumn<Dogovor> premiyaColumn = new TextColumn<Dogovor>() {
      @Override
      public String getValue(Dogovor dogovor) {
        return dogovor.getPremiya();
      }
    };
    cellTable.addColumn(premiyaColumn,"Премия");
    TextColumn<Dogovor> srokColumn = new TextColumn<Dogovor>() {
      @Override
      public String getValue(Dogovor dogovor) {
        return DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(dogovor.getStart())
                +" - "+DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(dogovor.getEnd());
      }
    };
    cellTable.addColumn(srokColumn,"Срок действия");

    List<Dogovor> list = new ArrayList<>();
    list.add(new Dogovor(1, new Date(), new Strahovatel("Иванов", "Иван","Михалыч"), new Date(), new Date(), "1000"));
    list.add(new Dogovor(2, new Date(), new Strahovatel("Петров", "Василий","Сергеевич"), new Date(), new Date(), "1000"));
    list.add(new Dogovor(3, new Date(), new Strahovatel("Сидоров", "Сидор","Иванович"), new Date(), new Date(), "1000"));
    list.add(new Dogovor(4, new Date(), new Strahovatel("Васечкин", "Петр","Петрович"), new Date(), new Date(), "1000"));
    cellTable.setRowData(list);

    // Add a handler to send the name to the server
    CreateDogovor handler = new CreateDogovor();
    txtbtnCreate.addClickHandler(handler);


//        verticalPanel.add(textBox);
//        verticalPanel.add(textArea);
//        verticalPanel.add(cellTable);

//        textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
//                                          @Override
//                                          public void onValueChange(ValueChangeEvent<String> event) {
//                                              textArea.setText(textArea.getText() + "\n" + textBox.getText());
//                                              textBox.setText("");
//
//                                          }
//                                      }
//        );

//        button.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                if (label.getText().equals("")) {
//                    TaskService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
//                } else {
//                    label.setText("");
//                }
//            }
//        });

    // Assume that the host HTML has elements defined whose
    // IDs are "slot1", "slot2".  In a real app, you probably would not want
    // to hard-code IDs.  Instead, you could, for example, search for all
    // elements with a particular CSS class and replace them with widgets.
    //

//        rootPanel.add(verticalPanel);
//        RootPanel.get("slot1").add(button);
//        RootPanel.get("slot2").add(label);


  }

  private static class MyAsyncCallback implements AsyncCallback<String> {
    private Label label;

    public MyAsyncCallback(Label label) {
      this.label = label;
    }

    public void onSuccess(String result) {
      label.getElement().setInnerHTML(result);
    }

    public void onFailure(Throwable throwable) {
      label.setText("Failed to receive answer from server!");
    }
  }
}
