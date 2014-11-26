package facades;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Person;

public class Facade implements facadeInterface {

    Map<Long, Person> persons = new HashMap();
    private final Gson gson = new Gson();
    private static Facade instance = new Facade();
    Person p = new Person();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARestPU");
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
    public String getPersonAsJson(long id) {
        Query query = em.createQuery("SELECT user FROM Credentials p WHERE user.id = ?1").setParameter(1, id);
        Person person = (Person) query.getSingleResult();
        return gson.toJson(person);
    }

}
