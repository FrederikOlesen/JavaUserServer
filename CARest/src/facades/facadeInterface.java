package facades;

import model.Person;
import model.Roleschool;

public interface facadeInterface {

    public String getPersonsAsJSON();

    public String getPersonAsJson(long id);

    public Person addPersonFromGson(String json);

    public Roleschool addRoleFromGson(String json, long id);

    public Person delete(long id);

}
