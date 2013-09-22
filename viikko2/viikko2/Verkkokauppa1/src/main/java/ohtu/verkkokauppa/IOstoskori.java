/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

/**
 *
 * @author mkortelainen
 */
public interface IOstoskori {

    int hinta();

    void lisaa(Tuote t);

    void poista(Tuote t);
    
}
