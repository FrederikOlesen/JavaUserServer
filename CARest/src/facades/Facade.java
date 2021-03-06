package facades;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

//    public static Facade getFacade(boolean reseet) {
//        //if (true) {
//            instance = new Facade();
//        //}
//        return instance;
//    }
    public Facade() {
    }

    //Method to retrieve person based upon an ID.
    @Override
    public String getPersonAsJson(String username) {

        System.out.println("You are inside getPerson");
        Credentials p = em.find(Credentials.class, username);
        System.out.println("P: " + p);

        return gson.toJson(p);

    }

    //Adds a new person to the database.
    @Override
    public Credentials addPersonFromGson(String json) {
        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject) jp.parse(json);

        String username = jo.get("username").getAsString();

        Credentials p = em.find(Credentials.class, username);

        if (p != null) {

            System.out.println("User is already defined");

        } else {

            System.out.println("Json: " + json);
            em.getTransaction().begin();
            p = gson.fromJson(json, Credentials.class);
            em.persist(p);
            em.getTransaction().commit();
        }

        return p;
    }

    @Override
    public Credentials changePassword(String json, String username) {

        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject) jp.parse(json);

        String password = jo.get("password").getAsString();
        String newpass = jo.get("confirmedpassword").getAsString();

        System.out.println("Json String:" + json);

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
    public Credentials delete(String username) {

        Credentials person = em.find(Credentials.class, username);

        if (person == null) {
            System.out.println("Cant find person");
        } else {
            System.out.println("You are in DELETE");
            em.getTransaction().begin();
            Credentials p = em.find(Credentials.class, username);
            em.remove(p);
            em.getTransaction().commit();

        }
        return p;

    }

    @Override
    public void close() {
        em.close();
    }
}
