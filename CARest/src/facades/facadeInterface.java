package facades;

import model.Credentials;

public interface facadeInterface {

    public String getPersonsAsJSON();

    public String getPersonAsJson(String username);

    public Credentials addPersonFromGson(String json);

    public Credentials delete(long id);

}
