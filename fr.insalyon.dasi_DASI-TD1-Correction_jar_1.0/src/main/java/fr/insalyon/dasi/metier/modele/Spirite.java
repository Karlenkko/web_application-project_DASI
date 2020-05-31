/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

/**
 *
 * @author 18740
 */
import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Spirite extends Medium implements Serializable{
    private String support;
    
    protected Spirite() {
    }
    
    public Spirite(String denomination, String presentation, String type, char genre, String support) {
        super(denomination,presentation,type, genre);
        this.support= support;
    }
    
    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
    
    @Override
    public String toString() {
        return super.toString()+",support"+support;
    }
    
}
