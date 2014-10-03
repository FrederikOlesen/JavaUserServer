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
    public void addPersonTest() {
        Person p = new Person("Frederik", "Olesen", "12344321", "Testmailen@testeren.dk");
        facade.addPersonFromGson(gson.toJson(p));

        final String personsAsJSON = facade.getPersonsAsJSON();

        assertEquals(true, personsAsJSON.contains("Testmailen@testeren.dk"));

    }

    @Test
    public void testGetPerson() throws Exception {
        Long lo = Long.parseLong("16");
        final String personAsJson = facade.getPersonAsJson(lo);

        assertEquals(true, personAsJson.contains("16"));
    }

    @Test
    public void getPersonsAsJSON() {

        Person p = new Person("Frederik", "Olesen", "12344321", "Testmailen@testeren.dk");
        facade.addPersonFromGson(gson.toJson(p));

        final String personsAsJSON = facade.getPersonsAsJSON();

        assertEquals(true, personsAsJSON.contains("Frederik"));

    }

}
