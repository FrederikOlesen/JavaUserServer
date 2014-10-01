/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import model.Person;
import model.Roleschool;

/**
 *
 * @author Mads
 */
public interface facadeInterface {

    public String getPersonsAsJSON();

    public String getPersonAsJson(long id);

    public Person addPersonFromGson(String json);

    public Roleschool addRoleFromGson(String json);

    public Person delete(long id);

}
