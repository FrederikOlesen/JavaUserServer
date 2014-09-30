/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Person;
import model.Roleschool;
import model.Student;

/**
 *
 * @author frederikolesen
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARestPU");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        //Person p = new Person("Frederik", "Olesen", "12345678", "Frederik.o@mailme.dk");
        //Person p1 = new Person("Michael", "Sutter", "12345678", "Michael@sutter.dk");
        Person p2 = new Person("Test", "Test123", "1234", "Asdsad@asd.com");
                
        //Roleschool role = new Teacher("Dickface");
        //Roleschool rol1 = new Student("3. semester");

//        em.persist(p);
//        em.persist(p1);
        em.persist(p2);
        //em.persist(role);
        //em.persist(rol1);

        em.getTransaction().commit();

    }

}
