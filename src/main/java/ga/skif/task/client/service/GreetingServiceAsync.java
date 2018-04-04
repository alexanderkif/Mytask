package ga.skif.task.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ga.skif.task.client.entity.Dogovor;
import ga.skif.task.client.entity.Strahovatel;

import java.util.Date;
import java.util.List;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

    void getListStrahovatels(String name, String name2, String lastname, AsyncCallback<List<Strahovatel>> async)
            throws IllegalArgumentException;

    void getStrahovatelByFioAndBirth(String name, String name2, String lastname, Date birth, AsyncCallback<Strahovatel> async)
            throws IllegalArgumentException;

    void getStrahovatelById(String id, AsyncCallback<Strahovatel> async)
            throws IllegalArgumentException;

    void saveStrahovatel(Strahovatel strahovatel, AsyncCallback<Boolean> async)
            throws IllegalArgumentException;

    void updateStrahovatelById(String id, Strahovatel strahovatel, AsyncCallback<Boolean> asyncCallback)
            throws IllegalArgumentException;

    void checkDogId(Integer id, AsyncCallback<Boolean> asyncCallback)
            throws IllegalArgumentException;

    void createDogovor(Dogovor dogovor, AsyncCallback<Boolean> asyncCallback)
            throws IllegalArgumentException;

    void findDogovor(Integer id, AsyncCallback<List<Dogovor>> dlist)
            throws IllegalArgumentException;
}
