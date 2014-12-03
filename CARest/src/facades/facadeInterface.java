package facades;


import model.Credentials;

public interface facadeInterface {

    public String getPersonAsJson(String username);

    public Credentials addPersonFromGson(String json);

    public Credentials changePassword(String json, String username);

    public Credentials delete(String username);

}
