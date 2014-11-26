package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity

public class PersonXxx implements Serializable {

    private static final long serialVersionUID = 1L;

    //Generating a unique ID using a sequence, which is supported by oracle.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_ID")
    @SequenceGenerator(name = "user_ID", sequenceName = "user_ID", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String passWord;

    public PersonXxx() {
    }

    public PersonXxx(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "Person{" + "userName=" + userName + ", passWord=" + passWord + '}';
    }

}
