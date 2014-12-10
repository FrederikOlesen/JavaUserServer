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

    String username = "cphtestjpa";
    String json = "{\"username\":\"cphtestjpa\",\"password\":\"Test\",\"role\":\"STUDENT\"}";

    public ProgramTest() {

    }

    @Before
    public void before() {
        facade = new Facade();
        //Test adding a new person.
        em.getTransaction().begin();
        Credentials p = facade.addPersonFromGson(json);

    }

    @Test
    public void testAddPerson() throws Exception {
        facade.delete(username);
        facade.addPersonFromGson(json);
        final String personAsJSONPass = facade.getPersonAsJson(username);
        assertEquals(true, personAsJSONPass.contains("{\"username\":\"cphtestjpa\",\"password\":\"Test\","
                + "\"role\":\"STUDENT\"}"));
    }

    @Test
    public void testGetPerson() throws Exception {

        //Test retrieving the added user.
        final String personAsJson = facade.getPersonAsJson(username);

        assertEquals(true, personAsJson.contains("{\"username\":\"cphtestjpa\",\"password\":\"Test\","
                + "\"role\":\"STUDENT\"}"));
    }

    @Test
    public void testChangePassword() throws Exception {
        //Test to change password.
        String password = "{\"username\":\"cphtestjpa\",\"password\":\"Test\""
                + ",\"confirmedpassword\":\"Test123\"}";

        facade.changePassword(password, username);
        final String personAsJSONPass = facade.getPersonAsJson(username);
        assertEquals(true, personAsJSONPass.contains("{\"username\":\"cphtestjpa\",\"password\":\"Test123\""
                + ",\"role\":\"STUDENT\"}"));
    }

    @Test
    public void testDeletePerson() {
        //Test the delete method.
        facade.delete(username);
        final String personAsJson1 = facade.getPersonAsJson(username);

        assertEquals(false, personAsJson1.contains("{\"username\":\"cphtestjpa\",\"password\":\"Test123\","
                + "\"role\":\"STUDENT\"}"));
    }

    @After
    public void afterDelete() {
        facade.delete(username);
    }

}
