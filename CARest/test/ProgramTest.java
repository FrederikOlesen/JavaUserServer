/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import facades.Facade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Person;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author frederikolesen
 */
public class ProgramTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARestPU");
    EntityManager em = emf.createEntityManager();
    private final Gson gson = new Gson();
    Facade facade;

    public ProgramTest() {

    }

    @Before
    public void before() {
        facade = Facade.getFacade(true);
        em.getTransaction().begin();
    }

//    @Test
//    public void addPersonTest() {
//        Person p2 = new Person("Test", "Test123", "1234", "Asdsad@asd.com");
//        em.persist(p2);
//
//        final Person find = em.find(Person.class, 6);
//
//        System.out.println("Find: " + find);
//    }
    @Test
    public void testAddPerson() {
        Person person = facade.addPersonFromGson(gson.toJson(new Person("Frederik", "Olesen", "12345678", "12321@sad.com")));

        String expectedJsonString = gson.toJson(person);
        String actual = facade.getPersonAsJson(person.getId());

        assertEquals(expectedJsonString, actual);

    }

//    @Test
//    public void testGetPerson() throws Exception {
//        testAddPerson();
//    }
//
//    @Test
//    public void getPersonsAsJSON() {
//        List<Person> poo;
//        poo = em.createQuery("SELECT p FROM PERSON p").getResultList();
//        System.out.println(poo.get(1));
//
//    }
//    @Test
//    public void testGetPersons() {
//        Person p = new Person("Frederik", "Olesen", "ASda@“asd.com", "12345678");
//        Person person1 = facade.addPersonFromGson(gson.toJson(p));
//        Person p2 = new Person("Mads", "Sutter", "ASda@“asd.com", "12345678");
//        Person person2 = facade.addPersonFromGson(gson.toJson(p2));
//
//        //Make the Expected String
//        Map<Long, Person> test = new HashMap();
//        test.put(person1.getId(), person1);
//        test.put(person2.getId(), person2);
//        String expected = gson.toJson(test.values());
//        String result = facade.getPersonsAsJSON();
//        System.out.println("Result: " + result);
//        assertEquals(expected, result);
//    }
//
//    @Test
//    public void testDeletePerson() {
//        Person person = facade.addPersonFromGson(gson.toJson(new Person("Test", "Test", "Asdas@2sad.com", "1234321")));
//        facade.delete(person.getId());
//        facade.getPersonAsJson(person.getId());
//
//    }
    @After
    public void after() {
        em.getTransaction().rollback();
    }

}
