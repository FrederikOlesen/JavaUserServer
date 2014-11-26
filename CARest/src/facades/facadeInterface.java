package facades;

import model.PersonXxx;

public interface facadeInterface {

    public String getPersonsAsJSON();

    public String getPersonAsJson(String username, String password);

    public PersonXxx addPersonFromGson(String json);

    public PersonXxx delete(long id);

}
