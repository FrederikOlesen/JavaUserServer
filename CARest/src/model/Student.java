/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Frederik
 */
@Entity
public class Student extends Roleschool implements Serializable {

    private static final long serialVersionUID = 1L;

    private String semester;

    public Student() {

    }

    public Student(String semester) {
        this.semester = semester;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

}
