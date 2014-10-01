/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.AssistentTeacher;
import model.Person;
import model.Roleschool;
import model.Student;
import model.Teacher;

/**
 *
 * @author Mads
 */
public class Facade implements facadeInterface {

    Map<Long, Person> persons = new HashMap();
    List<Person> poo;
    private final Gson gson = new Gson();
    private static Facade instance = new Facade();
    private Long nextId;
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

    @Override
    public String getPersonsAsJSON() {
        List<Person> result = em.createQuery("SELECT p FROM Person p").getResultList();
        System.err.println("Number of persons: " + result.size());
        return gson.toJson(result);
    }

    public void createTestData() {

        addPersonFromGson(gson.toJson(new Person("Frederik", "Olesen", "12345678", "Json test")));
        addPersonFromGson(gson.toJson(new Person("Michael", "Sutter", "12345678", "Json test")));
        addPersonFromGson(gson.toJson(new Person("Mads", "Sutter mere", "12345678", "Json test")));
    }

    @Override
    public String getPersonAsJson(long id) {
        Query query = em.createQuery("SELECT p FROM Person p WHERE p.id = ?1").setParameter(1, id);
        Person person = (Person) query.getSingleResult();
        return gson.toJson(person);
    }

    @Override
    public Person addPersonFromGson(String json) {
        Person p = gson.fromJson(json, Person.class);
//        Long test = Long.valueOf(1);
//        p.setId(test);
//        persons.put(test, p);
        return p;
    }

    @Override
    public Roleschool addRoleFromGson(String json, long id) {
       Roleschool role = null;
        if (json.contains("Teacher")) {
            role = gson.fromJson(json, Teacher.class);
        }
        if (json.contains("Student")) {
            role = gson.fromJson(json, Student.class);
        }
        if (json.contains("AssistentTeacher")) {
            role = gson.fromJson(json, AssistentTeacher.class);
        }

        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        if (person != null && role != null) {
            person.setRoles(role);
            //== Maybe use persist, or merge
        }
        em.getTransaction().commit();

        return role;
    }

    @Override
    public Person delete(long id) {
        Person p = persons.remove(id);
        if (p == null) {
            System.out.println("Person not found. Cant delete him");
        }
        return p;
    }

}
