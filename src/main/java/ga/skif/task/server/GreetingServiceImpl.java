package ga.skif.task.server;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ga.skif.task.client.GreetingService;
import ga.skif.task.client.Strahovatel;
import ga.skif.task.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

    String uri = "mongodb://user:fishuser@cluster0-shard-00-00-qbirv.mongodb.net:27017,cluster0-shard-00-01-qbirv.mongodb.net:27017,cluster0-shard-00-02-qbirv.mongodb.net:27017/fish?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin";
    MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
    MongoDatabase database = mongoClient.getDatabase("fish");
    MongoCollection strahovatels = database.getCollection("strahovatels");
//    MongoCollection dogovors = database.getCollection("dogovors");

  public String greetServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid. 
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);

    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }

  @Override
  public List<Strahovatel> greetServer(String name, String name2, String lastname) throws IllegalArgumentException {

      return (List<Strahovatel>) strahovatels.find(new Document("name", name)
              .append("name2", name2).append("lastname", lastname)).into(new ArrayList());

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
