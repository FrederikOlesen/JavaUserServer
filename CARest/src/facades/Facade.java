/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import model.Person;
import model.Roleschool;

/**
 *
 * @author Mads
 */
public class Facade implements facadeInterface {

    Map<Long, Person> persons = new HashMap();
    private final Gson gson = new Gson();
    private static Facade instance = new Facade();
    private Long nextId;

    public static Facade getFacade(boolean reseet) {
        if (true) {
            instance = new Facade();
        }
        return instance;
    }

    @Override
    public String getPersonsAsJSON() {
        if (persons.isEmpty()) {
            return null;
        }
        return gson.toJson(persons.values());
    }

    public void createTestData() {

        addPersonFromGson(gson.toJson(new Person("Frederik", "Olesen", "12345678", "Json test")));
        addPersonFromGson(gson.toJson(new Person("Michael", "Sutter", "12345678", "Json test")));
        addPersonFromGson(gson.toJson(new Person("Mads", "Sutter mere", "12345678", "Json test")));
    }

    @Override
    public String getPersonAsJson(long id) {
        Person p = persons.get(id);
        if (p == null) {
            System.out.println("Person dosent exist");
        }
        return gson.toJson(p);
    }

    @Override
    public Person addPersonFromGson(String json) {
        Person p = gson.fromJson(json, Person.class);
//        Long test = Long.valueOf(1);
//        p.setId(test);
//        persons.put(test, p);
        return p;
    }

    @Override
    public Roleschool addRoleFromGson(String json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person delete(long id) {
        Person p = persons.remove(id);
        if (p == null) {
            System.out.println("Person not found. Cant delete him");
        }
        return p;
    }

}
