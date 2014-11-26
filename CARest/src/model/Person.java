package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    //Generating a unique ID using a sequence, which is supported by oracle.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personID")
    @SequenceGenerator(name = "personID", sequenceName = "PERSON_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Collection<Roleschool> roles;

    private String mail;

    private String phone;

    public Person() {
    }

    public Person(String firstName, String lastName, String mail, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<Roleschool> getRoles() {
        return roles;
    }

    public void setRoles(Roleschool role) {
        roles.add(role);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", mail=" + mail + ", phone=" + phone + '}';
    }

}
