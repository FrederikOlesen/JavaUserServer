package facades;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.PersonXxx;

public class Facade implements facadeInterface {

    Map<Long, PersonXxx> persons = new HashMap();
    private final Gson gson = new Gson();
    private static Facade instance = new Facade();
    PersonXxx p = new PersonXxx();

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

    //Method to retrieve all persons.
    @Override
    public String getPersonsAsJSON() {
        List<PersonXxx> result = em.createQuery("SELECT p FROM Person p").getResultList();
        return gson.toJson(result);
    }

    //Method to retrieve person based upon an ID.
    @Override
    public String getPersonAsJson(String username, String password) {
        System.out.println("Inside getPersonAsJson");
        Query query = em.createQuery("SELECT p FROM Credentials p WHERE USERNAME = ?1 AND PASSWORD =?2").setParameter(1, username).setParameter(2, password);
        System.out.println("After query" + query);
        PersonXxx person = (PersonXxx) query.getSingleResult();
        System.out.println("Person: " + person);
        return gson.toJson(person);
    }

    //Adds a new person to the database.
    @Override
    public PersonXxx addPersonFromGson(String json) {
        em.getTransaction().begin();
        PersonXxx p = gson.fromJson(json, PersonXxx.class);
        em.persist(p);
        em.getTransaction().commit();
        return p;
    }

    //Delete a person.
    @Override
    public PersonXxx delete(long id) {
        em.getTransaction().begin();
        PersonXxx p = em.find(PersonXxx.class, id);
        em.remove(p);
        em.getTransaction().commit();
        return p;
    }
}
