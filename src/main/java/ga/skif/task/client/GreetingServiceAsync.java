package ga.skif.task.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
  void greetServer(String input, AsyncCallback<String> callback)
      throws IllegalArgumentException;

    void greetSearch(String name, String name2, String lastname, AsyncCallback<List<Strahovatel>> async)
            throws IllegalArgumentException;

    void greetSave(Strahovatel strahovatel, AsyncCallback<Boolean> async);
}
