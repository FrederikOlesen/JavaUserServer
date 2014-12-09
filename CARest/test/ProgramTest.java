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

        //Test adding a new person.
        Credentials p = gson.fromJson(json, Credentials.class);

        em.persist(p);

        em.getTransaction().commit();

        //Test retrieving the added user.
        final String personAsJson = facade.getPersonAsJson(username);

        assertEquals(true, personAsJson.contains("{\"username\":\"cphtestjpa\",\"password\":\"Test\",\"role\":\"STUDENT\"}"));

        //Test to change password.
        String password = "{\"username\":\"cphtestjpa\",\"password\":\"Test\",\"confirmedpassword\":\"Test123\"}";

        facade.changePassword(password, username);

        final String personAsJSONPass = facade.getPersonAsJson(username);

        assertEquals(true, personAsJSONPass.contains("{\"username\":\"cphtestjpa\",\"password\":\"Test123\",\"role\":\"STUDENT\"}"));

        //Test the delete method.
        facade.delete(username);

        final String personAsJson1 = facade.getPersonAsJson(username);

        assertEquals(false, personAsJson1.contains("{\"username\":\"cphtestjpa\",\"password\":\"Test123\",\"role\":\"STUDENT\"}"));

    }

}
