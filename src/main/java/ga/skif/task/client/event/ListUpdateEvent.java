package ga.skif.task.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ListUpdateEvent extends GwtEvent<ListUpdateEventHandler> {

    public static Type<ListUpdateEventHandler> TYPE = new Type<>();

    @Override
    public Type<ListUpdateEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ListUpdateEventHandler listUpdateEventHandler) {
        listUpdateEventHandler.onListUpdate(this);
    }
}
