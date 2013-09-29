/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author mkortelainen
 */
@Entity
public class Rating {
    @Id
    private Integer id;

    @ManyToOne
    private Beer beer;

    @ManyToOne
    private Users user;
 
    private int value;
    
    // konstruktorit, setterit ja getterit
    public Rating(){}
    
    public Rating(Beer beer, Users user, int value){
        this.beer = beer;
        this.user = user;
        this.value = value;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the beer
     */
    public Beer getBeer() {
        return beer;
    }

    /**
     * @param beer the beer to set
     */
    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    /**
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}
