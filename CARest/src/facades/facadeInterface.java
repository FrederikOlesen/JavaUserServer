package facades;

import java.io.IOException;
import model.Credentials;

public interface facadeInterface {

    public String getPersonAsJson(String username);

    public Credentials addPersonFromGson(String json) throws IOException;

    public Credentials changePassword(String json, String username);

    public Credentials delete(long id);

}
