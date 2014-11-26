import facades.Facade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Person;
import org.junit.Test;
import org.junit.Before;
import com.google.gson.Gson;
import static org.junit.Assert.*;

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
    }

    @Test
    public void testGetPerson() throws Exception {
        //Parsing 16 to a long, as getPersonAsJson is taking in a long id.
        Long lo = Long.parseLong("16");
        String username = "";
        String password = "";
        final String personAsJson = facade.getPersonAsJson(username, password);

        //Checking if there is a Person with the ID on 16.
        assertEquals(true, personAsJson.contains("16"));
    }

}
