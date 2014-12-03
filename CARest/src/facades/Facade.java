package facades;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Credentials;

public class Facade implements facadeInterface {

    Map<Long, Credentials> persons = new HashMap();
    private final Gson gson = new Gson();
    private static Facade instance = new Facade();
    Credentials p = new Credentials();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARESTPU");
    EntityManager em = emf.createEntityManager();

    public static Facade getFacade(boolean reseet) {
        if (true) {
            instance = new Facade();
        }
        return instance;
    }

    public Facade() {
    }

    //Method to retrieve person based upon an ID.
    @Override
    public String getPersonAsJson(String username) {
        System.out.println("You are inside getPerson");
        Credentials p = em.find(Credentials.class, username);
        return gson.toJson(p);
    }

    //Adds a new person to the database.
    @Override
    public Credentials addPersonFromGson(String json) throws IOException {

        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject) jp.parse(json);

        String username = jo.get("username").getAsString();
        String password = jo.get("password").getAsString();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        Credentials person = em.find(Credentials.class, username);
        if (person == null) {
            throw new IOException("User found with that username");
        } else {
            em.getTransaction().begin();
            Credentials p = gson.fromJson(json, Credentials.class);
            em.persist(p);
            em.getTransaction().commit();
            return p;
        }

    }

    @Override
    public Credentials changePassword(String json, String username) {

        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject) jp.parse(json);

        String password = jo.get("password").getAsString();
        String newpass = jo.get("confirmedpassword").getAsString();

        System.out.println("Password:" + password);

        Credentials person = em.find(Credentials.class, username);

        if (person == null) {
            System.out.println("Error finding the person.");
        } else {
            em.getTransaction().begin();

            if (person.getPassword().equals(password)) {
                person.setPassword(newpass);
                em.getTransaction().commit();
            } else {
                System.out.println("Password are not equal");
            }
        }
        return person;
    }

    //Delete a person.
    @Override
    public Credentials delete(long id) {
        em.getTransaction().begin();
        Credentials p = em.find(Credentials.class, id);
        em.remove(p);
        em.getTransaction().commit();
        return p;
    }
}
