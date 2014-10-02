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
public class Teacher extends Roleschool implements Serializable {

    private static final long serialVersionUID = 1L;

    private String degree;

    public Teacher() {

    }

    public Teacher(String degree) {

        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

}
