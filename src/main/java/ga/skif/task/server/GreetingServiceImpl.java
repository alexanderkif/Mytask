package ga.skif.task.server;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import ga.skif.task.client.GreetingService;
import ga.skif.task.client.Strahovatel;
import ga.skif.task.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
        GreetingService {

    String uri = "mongodb://user:fishuser@cluster0-shard-00-00-qbirv.mongodb.net:27017,cluster0-shard-00-01-qbirv.mongodb.net:27017,cluster0-shard-00-02-qbirv.mongodb.net:27017/fish?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin";
    MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
    MongoDatabase database = mongoClient.getDatabase("fish");
    MongoCollection<Document> strahovatels = database.getCollection("strahovatels");
//    MongoCollection dogovors = database.getCollection("dogovors");

//    public String greetServer(String input) throws IllegalArgumentException {
//        // Verify that the input is valid.
//        if (!FieldVerifier.isValidName(input)) {
//            // If the input is not valid, throw an IllegalArgumentException back to
//            // the client.
//            throw new IllegalArgumentException(
//                    "Name must be at least 4 characters long");
//        }
//
//        String serverInfo = getServletContext().getServerInfo();
//        String userAgent = getThreadLocalRequest().getHeader("User-Agent");
//
//        // Escape data from the client to avoid cross-site script vulnerabilities.
//        input = escapeHtml(input);
//        userAgent = escapeHtml(userAgent);
//
//        return "Hello, " + input + "!<br><br>I am running " + serverInfo
//                + ".<br><br>It looks like you are using:<br>" + userAgent;
//    }

    @Override
    public List<Strahovatel> greetSearch(String name, String name2, String lastname) throws IllegalArgumentException {
        List<Strahovatel> list = new ArrayList<>();
        if (name.equals("") && name2.equals("") && lastname.equals("")){
            strahovatels.find()
                    .forEach((Block<Document>) s -> list.add(toStrahovatel((Document) s)));
        }else {
            strahovatels.find(and(
                    eq("firstName", name),
                    eq("firstName2", name2),
                    eq("lastName", lastname)))
                    .forEach((Block<Document>) s -> list.add(toStrahovatel((Document) s)));
        }
        System.out.println("=====greetSearch=====returned===list.get(0)====");
        System.out.println(list.get(0).toString());
        return list;
    }

    @Override
    public Strahovatel greetSearchFirst(String name, String name2, String lastname, Date birth) throws IllegalArgumentException {
        Document doc = strahovatels.find(and(
                eq("firstName", name),
                eq("firstName2", name2),
                eq("lastName", lastname),
                eq("birth", birth)))
                .first();
        System.out.println("=====greetSearchFirst=====returned");
        System.out.println(doc);
        return toStrahovatel(doc);
    }

    @Override
    public Boolean greetSave(Strahovatel strahovatel) throws IllegalArgumentException {
        try {
            strahovatels.insertOne(toDocument(strahovatel));
            System.out.println("=====greetSave=====");
            System.out.println(strahovatel.getId());
            System.out.println(toDocument(strahovatel));
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public Boolean greetUpdate(String id, Strahovatel strah) throws IllegalArgumentException {
        try {
            UpdateResult result = strahovatels.updateOne(eq("_id", new ObjectId(id)), new Document("$set",
                    new Document("lastName", strah.getLastName())
                    .append("firstName", strah.getFirstName())
                    .append("firstName2", strah.getFirstName2())
                    .append("birth", strah.getBirth())
                    .append("passportSeria", strah.getPassportSeria())
                    .append("passportNumber", strah.getPassportNumber())));
            System.out.println("=====greetUpdate=====returned");
            System.out.println(id);
            System.out.println(strah.toString());
            System.out.println(result);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkDogNumber(Integer number) throws IllegalArgumentException {
        try {
            return strahovatels.count(eq("number", number)) > 0;
        } catch (Exception e) {
            return true;
        }
    }

    public Strahovatel toStrahovatel(Document document) {
        Strahovatel strahovatel1 = new Strahovatel();
        strahovatel1.setId(document.get("_id").toString());
        strahovatel1.setLastName(document.get("lastName").toString());
        strahovatel1.setFirstName(document.get("firstName").toString());
        strahovatel1.setFirstName2(document.get("firstName2").toString());
        strahovatel1.setBirth((Date) document.get("birth"));
        strahovatel1.setPassportSeria((Integer) document.get("passportSeria"));
        strahovatel1.setPassportNumber((Integer) document.get("passportNumber"));
        return strahovatel1;
    }

    public Document toDocument(Strahovatel strahovatel) {
        Document document = new Document("lastName", strahovatel.getLastName());
        document.append("firstName", strahovatel.getFirstName());
        document.append("firstName2", strahovatel.getFirstName2());
        document.append("birth", strahovatel.getBirth());
        document.append("passportSeria", strahovatel.getPassportSeria());
        document.append("passportNumber", strahovatel.getPassportNumber());
        return document;
    }

    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     *
     * @param html the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
                ">", "&gt;");
    }
}
