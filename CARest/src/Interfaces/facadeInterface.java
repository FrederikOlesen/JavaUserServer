/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import model.PersonEntity;
import model.RoleSchool;

/**
 *
 * @author Mads
 */
public interface facadeInterface {

    public String getPersonsAsJSON(); 
    
    public String getPersonsAsJson(long id); 
    
    public PersonEntity addPersonFromGson(String json);
    
    public RoleSchool addRoleFromGson(String json); 
    
    public PersonEntity delete(long id); 
    
}
