/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.PersonEntity;

/**
 *
 * @author frederikolesen
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARestPU");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        PersonEntity p = new PersonEntity("test Sucks balls og det samme gør dit face", "He123123123j", "12345678", "Test@dinmor.dk");

        em.persist(p);

        em.getTransaction().commit();

    }

}
