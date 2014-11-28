import facades.Facade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import org.junit.Before;
import com.google.gson.Gson;
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

        String username = "MADS";
        final String personAsJson = facade.getPersonAsJson(username);

        assertEquals(true, personAsJson.contains("{\"username\":\"MADS\",\"password\":\"TEST\",\"role\":\"STUDENT\"}"));
    }

}
