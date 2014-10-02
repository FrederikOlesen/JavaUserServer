/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import facades.Facade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.google.gson.Gson;

/**
 *
 * @author frederikolesen
 */
public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Facade facade = new Facade();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARestPU");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        //Person p = facade.addPersonFromGson(gson.toJson(new Person("Mads1", "Sutter", "Teeeeest123", "1231231231212312321")));
        //em.persist(p);
        em.getTransaction().commit();

    }
}
