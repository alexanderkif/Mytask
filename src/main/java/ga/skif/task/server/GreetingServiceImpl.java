package ga.skif.task.server;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import ga.skif.task.client.AddressOb;
import ga.skif.task.client.Dogovor;
import ga.skif.task.client.GreetingService;
import ga.skif.task.client.Strahovatel;
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

    private String uri = "mongodb://user:fishuser@cluster0-shard-00-00-qbirv.mongodb.net:27017,cluster0-shard-00-01-qbirv.mongodb.net:27017,cluster0-shard-00-02-qbirv.mongodb.net:27017/fish?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin";
    private MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
    private MongoDatabase database = mongoClient.getDatabase("fish");
    private MongoCollection<Document> strahovatels = database.getCollection("strahovatels");
    private MongoCollection<Document> dogovors = database.getCollection("dogovors");

    @Override
    public List<Strahovatel> greetSearch(String name, String name2, String lastname) throws IllegalArgumentException {
        List<Strahovatel> list = new ArrayList<>();
        if (name.equals("") && name2.equals("") && lastname.equals("")) {
            strahovatels.find()
                    .forEach((Block<Document>) s -> list.add(toStrahovatel((Document) s)));
        } else {
            strahovatels.find(and(
                    eq("firstName", name),
                    eq("firstName2", name2),
                    eq("lastName", lastname)))
                    .forEach((Block<Document>) s -> list.add(toStrahovatel((Document) s)));
        }
        return list;
    }

    @Override
    public Strahovatel greetSearchFirstId(String id) throws IllegalArgumentException {
        Document doc = strahovatels.find(eq("_id", new ObjectId(id)))
                .first();
        return toStrahovatel(doc);
    }

    @Override
    public Strahovatel greetSearchFirst(String name, String name2, String lastname, Date birth) throws IllegalArgumentException {
        Document doc = strahovatels.find(and(
                eq("firstName", name),
                eq("firstName2", name2),
                eq("lastName", lastname),
                eq("birth", birth)))
                .first();
        return toStrahovatel(doc);
    }

    @Override
    public Boolean greetSave(Strahovatel strahovatel) throws IllegalArgumentException {
        try {
            strahovatels.insertOne(toDocument(strahovatel));
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public Boolean greetUpdate(String id, Strahovatel strah) throws IllegalArgumentException {
        try {
            strahovatels.updateOne(eq("_id", new ObjectId(id)), new Document("$set",
                    new Document("lastName", strah.getLastName())
                            .append("firstName", strah.getFirstName())
                            .append("firstName2", strah.getFirstName2())
                            .append("birth", strah.getBirth())
                            .append("passportSeria", strah.getPassportSeria())
                            .append("passportNumber", strah.getPassportNumber())));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkDogId(Integer id) throws IllegalArgumentException {
        try {
            return dogovors.count(eq("_id", id)) > 0;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean createDogovor(Dogovor dogovor) throws IllegalArgumentException {
        try {
            dogovors.insertOne(toDocument(dogovor));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Dogovor> findDogovor(Integer id) throws IllegalArgumentException {
        List<Dogovor> list = new ArrayList<>();
        if (id==0) {
            dogovors.find().into(new ArrayList<>())
                    .forEach(s -> list.add(toDogovors((Document) s)));
        } else {
            dogovors.find(eq("_id", id)).into(new ArrayList<>())
                    .forEach(s -> list.add(toDogovors((Document) s)));
        }
        return list;
    }

    private Dogovor toDogovors(Document document) {
        Dogovor dogovor = new Dogovor();
        dogovor.setId((Integer) document.get("_id"));
        dogovor.setDataZakl((Date) document.get("dataZakl"));
        dogovor.setStrahovatel(toStrahovatel((Document) document.get("strahovatel")));//problem
        dogovor.setAddressOb(toAddressOb((Document) document.get("addressOb")));
        dogovor.setStrSumma((Integer) document.get("strSumma"));
        dogovor.setStart((Date) document.get("start"));
        dogovor.setEnd((Date) document.get("end"));
        dogovor.setType((String) document.get("type"));
        dogovor.setYear((String) document.get("year"));
        dogovor.setSquair((String) document.get("squair"));
        dogovor.setDateRasheta((Date) document.get("dateRasheta"));
        dogovor.setPremiya((String) document.get("premiya"));
        return dogovor;
    }

    private AddressOb toAddressOb(Document document) {
        AddressOb addressOb = new AddressOb();
        addressOb.setState((String) document.get("state"));
        addressOb.setIndex((String) document.get("index"));
        addressOb.setKrai((String) document.get("krai"));
        addressOb.setDistrict((String) document.get("district"));
        addressOb.setTown((String) document.get("town"));
        addressOb.setStreet((String) document.get("street"));
        addressOb.setHome((Integer) document.get("home"));
        addressOb.setKorpus((String) document.get("korpus"));
        addressOb.setStroenie((String) document.get("stroenie"));
        addressOb.setFlat((Integer) document.get("flat"));
        addressOb.setComment((String) document.get("comment"));
        return addressOb;
    }

    public Strahovatel toStrahovatel(Document document) {
        Strahovatel strahovatel1 = new Strahovatel();
        try {
            strahovatel1.setId(document.get("_id").toString());
        }catch (Exception ignored){}
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
        if (!strahovatel.getId().equals("")) {
            document.append("_id", strahovatel.getId());
        }
        return document;
    }

    public Document toDocument(AddressOb addressOb) {
        Document document = new Document();
        document.append("state", addressOb.getState());
        document.append("index", addressOb.getIndex());
        document.append("krai", addressOb.getKrai());
        document.append("district", addressOb.getDistrict());
        document.append("town", addressOb.getTown());
        document.append("street", addressOb.getStreet());
        document.append("home", addressOb.getHome());
        document.append("korpus", addressOb.getKorpus());
        document.append("stroenie", addressOb.getStroenie());
        document.append("flat", addressOb.getFlat());
        document.append("comment", addressOb.getComment());
        return document;
    }

    public Document toDocument(Dogovor dogovor) {
        Document document = new Document();
        document.append("_id", dogovor.getId());
        document.append("dataZakl", dogovor.getDataZakl());
        document.append("strahovatel", toDocument(dogovor.getStrahovatel()));
        document.append("addressOb", toDocument(dogovor.getAddressOb()));
        document.append("strSumma", dogovor.getStrSumma());
        document.append("start", dogovor.getStart());
        document.append("end", dogovor.getEnd());
        document.append("type", dogovor.getType());
        document.append("year", dogovor.getYear());
        document.append("squair", dogovor.getSquair());
        document.append("dateRasheta", dogovor.getDateRasheta());
        document.append("premiya", dogovor.getPremiya());
        return document;
    }
}
