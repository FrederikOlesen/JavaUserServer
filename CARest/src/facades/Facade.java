package facades;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
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

    //Method to retrieve all persons.
    @Override
    public String getPersonsAsJSON() {
        List<Person> result = em.createQuery("SELECT p FROM Person p").getResultList();
        return gson.toJson(result);
    }

    //Method to retrieve person based upon an ID.
    @Override
    public String getPersonAsJson(String username, String password) {
        System.out.println("Inside getPersonAsJson");
        Query query = em.createQuery("SELECT p FROM Credentials WHERE p.userName = ?1 AND p.password =?2").setParameter(1, username).setParameter(2, password);
        System.out.println("After query");
        Person person = (Person) query.getSingleResult();
        return gson.toJson(person);
    }

    //Adds a new person to the database.
    @Override
    public Person addPersonFromGson(String json) {
        em.getTransaction().begin();
        Person p = gson.fromJson(json, Person.class);
        em.persist(p);
        em.getTransaction().commit();
        return p;
    }

    //Delete a person.
    @Override
    public Person delete(long id) {
        em.getTransaction().begin();
        Person p = em.find(Person.class, id);
        em.remove(p);
        em.getTransaction().commit();
        return p;
    }
}
