package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Roleschool;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-26T09:27:03")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> firstName;
    public static volatile SingularAttribute<Person, String> lastName;
    public static volatile SingularAttribute<Person, String> mail;
    public static volatile SingularAttribute<Person, String> phone;
    public static volatile CollectionAttribute<Person, Roleschool> roles;
    public static volatile SingularAttribute<Person, Long> id;

}