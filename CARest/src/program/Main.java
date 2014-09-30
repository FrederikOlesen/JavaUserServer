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
import model.Teacher;

/**
 *
 * @author frederikolesen
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CARestPU");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Person p = new Person("Frederik", "Olesen", "12345678", "Frederik.o@mailme.dk");
        //Roleschool role = new Teacher("Dickface");
        Roleschool rol1 = new Student("3. semester");

        em.persist(p);
        //em.persist(role);
        em.persist(rol1);

        em.getTransaction().commit();

    }

}
