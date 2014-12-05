import facades.Facade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import org.junit.Before;
import com.google.gson.Gson;
import model.Credentials;
import org.junit.After;
import static org.junit.Assert.*;

public class ProgramTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARESTPU");
    EntityManager em = emf.createEntityManager();

    private final Gson gson = new Gson();
    Facade facade;

    public ProgramTest() {

    }

    @Before
    public void before() {
        facade = Facade.getFacade(true);

    }

    @Test
    public void testGetPerson() throws Exception {

        em.getTransaction().begin();

        String json = "{\"username\":\"cphtest\",\"password\":\"Test\",\"role\":\"STUDENT\"}";

        Credentials p = gson.fromJson(json, Credentials.class);

        em.persist(p);

        em.getTransaction().commit();

        String username = "cphtest1";

        final String personAsJson = facade.getPersonAsJson(username);

        System.out.println("PersonasJson:" + personAsJson);

        assertEquals(true, personAsJson.contains("{\"username\":\"cphtest\",\"password\":\"Test\",\"role\":\"STUDENT\"}"));

        em.getTransaction().rollback();
    }

    @After
    public void tearDown() {
        em.getTransaction().rollback();
    }

}
