package ga.skif.task.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;
import java.util.List;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

    void greetSearch(String name, String name2, String lastname, AsyncCallback<List<Strahovatel>> async)
            throws IllegalArgumentException;

    void greetSearchFirst(String name, String name2, String lastname, Date birth, AsyncCallback<Strahovatel> async)
            throws IllegalArgumentException;

    void greetSave(Strahovatel strahovatel, AsyncCallback<Boolean> async)
            throws IllegalArgumentException;

    void greetUpdate(String id, Strahovatel strahovatel, AsyncCallback<Boolean> asyncCallback)
            throws IllegalArgumentException;

    void checkDogNumber(Integer number, AsyncCallback<Boolean> asyncCallback)
            throws IllegalArgumentException;
}
