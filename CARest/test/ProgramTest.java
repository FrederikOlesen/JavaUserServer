/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.PersonEntity;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
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
        PersonEntity p = new PersonEntity("Frederik", "Olesen", "53288942", "Frederik.o@mailme.dk");
        em.persist(p);

        final PersonEntity find = em.find(PersonEntity.class, 200000);
        
        System.out.println("Find: " + find);
    }

    @After
    public void after() {
        em.getTransaction().rollback();
    }

}
