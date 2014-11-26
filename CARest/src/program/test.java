/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Credentials;

/**
 *
 * @author frederikolesen
 */
public class test {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARESTPU");
        EntityManager em = emf.createEntityManager();

        String username = "MADS";

        Credentials p = em.find(Credentials.class, username);
        System.out.println("Password: " + p.getPassword());

        System.out.println("Person: " + p);
    }
}
