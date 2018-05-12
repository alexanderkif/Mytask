package ga.saha.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ChooseClientEventHandler extends EventHandler {

    void onChooseClient(ChooseClientEvent listUpdateEvent);
}
