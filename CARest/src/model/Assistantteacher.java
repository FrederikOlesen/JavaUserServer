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
public class Assistantteacher extends Roleschool implements Serializable {

    private static final long serialVersionUID = 1L;

    private String degree;

    public Assistantteacher() {

    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

}
