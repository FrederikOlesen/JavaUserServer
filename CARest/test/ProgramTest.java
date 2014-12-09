import facades.Facade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import org.junit.Before;
import com.google.gson.Gson;
import model.Credentials;
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
        facade = new Facade();

        em.getTransaction().begin();

    }

    @Test
    public void testGetPerson() throws Exception {

        String username = "cphtestjpa";
        String json = "{\"username\":\"cphtestjpa\",\"password\":\"Test\",\"role\":\"STUDENT\"}";

        Credentials p = gson.fromJson(json, Credentials.class);

        em.persist(p);

        em.getTransaction().commit();

        final String personAsJson = facade.getPersonAsJson(username);

        assertEquals(true, personAsJson.contains("{\"username\":\"cphtestjpa\",\"password\":\"Test\",\"role\":\"STUDENT\"}"));

        facade.delete(username);

        final String personAsJson1 = facade.getPersonAsJson(username);

        assertEquals(false, personAsJson1.contains("{\"username\":\"cphtestjpa\",\"password\":\"Test\",\"role\":\"STUDENT\"}"));

    }

}
