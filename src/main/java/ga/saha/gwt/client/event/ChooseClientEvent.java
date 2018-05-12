package ga.saha.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ChooseClientEvent extends GwtEvent<ChooseClientEventHandler> {

    public static Type<ChooseClientEventHandler> TYPE = new Type<>();

    @Override
    public Type<ChooseClientEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChooseClientEventHandler chooseClientEventHandler) {
        chooseClientEventHandler.onChooseClient(this);
    }
}
