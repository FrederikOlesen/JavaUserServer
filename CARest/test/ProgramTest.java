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
        //Testing addPerson by creating a new person.
        Person p = new Person("Frederik", "Olesen", "12344321", "Testmailen@testeren.dk");
        facade.addPersonFromGson(gson.toJson(p));

        //Using the getPersonAsJSON method from the facade to pull out the created user.
        final String personsAsJSON = facade.getPersonsAsJSON();

        //Checking rather the returned string, personAsJSON, contains an user with that email.
        assertEquals(true, personsAsJSON.contains("Testmailen@testeren.dk"));

    }

    @Test
    public void testGetPerson() throws Exception {
        //Parsing 16 to a long, as getPersonAsJson is taking in a long id.
        Long lo = Long.parseLong("16");
        final String personAsJson = facade.getPersonAsJson(lo);

        //Checking if there is a Person with the ID on 16.
        assertEquals(true, personAsJson.contains("16"));
    }

    @Test
    public void getPersonsAsJSON() {
        //Pulling out all data from the database.
        Person p = new Person("Frederik", "Olesen", "12344321", "Testmailen@testeren.dk");
        facade.addPersonFromGson(gson.toJson(p));

        final String personsAsJSON = facade.getPersonsAsJSON();

        assertEquals(true, personsAsJSON.contains("Frederik"));

    }

}
