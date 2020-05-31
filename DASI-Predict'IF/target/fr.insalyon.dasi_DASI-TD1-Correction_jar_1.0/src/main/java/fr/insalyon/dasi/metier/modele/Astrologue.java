/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Lenovo
 */
@Entity
public class Astrologue extends Medium implements Serializable {
    private String formation;
    private String promotion;
    
    protected Astrologue() {
        super();
    }
    
    public Astrologue(String denomination, String presentation, String type, char genre, String formation,String promotion) {
        super(denomination,presentation,type, genre);
        this.formation=formation;
        this.promotion=promotion;
    }
    
    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }
    
    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
    
    @Override
    public String toString() {
        return super.toString()+" ,formation"+formation+" ,promotion"+promotion;
    }
}
