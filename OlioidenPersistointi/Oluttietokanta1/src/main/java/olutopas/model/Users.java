/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.model;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author mkortelainen
 */
@Entity
public class Users {
    
    private String name;
    
    @Id
    private int id;
   
    private String password;
    
    public Users(){
        
    }
    

    /**
     * @return the Id
     */
    public int getId() {
        return id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.id = Id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
