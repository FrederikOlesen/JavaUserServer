package facades;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Assistantteacher;
import model.Person;
import model.Roleschool;
import model.Student;
import model.Teacher;

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
    public String getPersonAsJson(long id) {
        Query query = em.createQuery("SELECT p FROM Person p WHERE p.id = ?1").setParameter(1, id);
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

    //Add a role to an existing person found in the database using the id.
    @Override
    public Roleschool addRoleFromGson(String json, long id) {
        Roleschool role = null;
        if (json.contains("Teacher")) {
            role = gson.fromJson(json, Teacher.class);
        }
        if (json.contains("Student")) {
            role = gson.fromJson(json, Student.class);
        }
        if (json.contains("Assistantteacher")) {
            role = gson.fromJson(json, Assistantteacher.class);
        }

        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        if (person != null && role != null) {
            person.setRoles(role);
        }
        em.getTransaction().commit();

        return role;
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
