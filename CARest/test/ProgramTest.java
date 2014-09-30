/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Person;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author frederikolesen
 */
public class ProgramTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARestPU");

    EntityManager em = emf.createEntityManager();

    public ProgramTest() {

    }

    @Before
    public void before() {
        em.getTransaction().begin();
    }

    @Test
    public void addPersonTest() {
        Person p2 = new Person("Test", "Test123", "1234", "Asdsad@asd.com");
        em.persist(p2);

        final Person find = em.find(Person.class, 6);

        System.out.println("Find: " + find);
    }

    @After
    public void after() {
        em.getTransaction().rollback();
    }

}
