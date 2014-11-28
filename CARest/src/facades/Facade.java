package facades;

import com.google.gson.Gson;
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
        Credentials p = em.find(Credentials.class, username);
        return gson.toJson(p);
    }

    //Adds a new person to the database.
    @Override
    public Credentials addPersonFromGson(String json) {
        em.getTransaction().begin();
        Credentials p = gson.fromJson(json, Credentials.class);
        em.persist(p);
        em.getTransaction().commit();
        return p;
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
