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
public class Cartomacien extends Medium implements Serializable{
    protected Cartomacien() {
    }
    
    public Cartomacien(String denomination, String presentation, String type, char genre){
        super(denomination,presentation,type, genre);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
