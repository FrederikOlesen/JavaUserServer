package facades;

import model.Person;

public interface facadeInterface {

    public String getPersonsAsJSON();

    public String getPersonAsJson(String username, String password);

    public Person addPersonFromGson(String json);

    public Person delete(long id);

}
