package ga.skif.task.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.Date;
import java.util.List;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

    List<Strahovatel> greetSearch(String name, String name2, String lastname) throws IllegalArgumentException;

    Strahovatel greetSearchFirst(String name, String name2, String lastname, Date birth) throws IllegalArgumentException;

    Boolean greetSave(Strahovatel strahovatel) throws IllegalArgumentException;

    Boolean greetUpdate(String id, Strahovatel strahovatel) throws IllegalArgumentException;

    boolean checkDogNumber(Integer number) throws IllegalArgumentException;
}
