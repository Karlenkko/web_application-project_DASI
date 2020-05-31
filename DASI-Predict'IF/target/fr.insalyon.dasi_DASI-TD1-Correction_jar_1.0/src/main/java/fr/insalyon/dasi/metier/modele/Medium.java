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

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Medium implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String denomination;
    protected String presentation;
    @Column(unique = true)
    protected String type;
    protected int nbrConsultation;
    protected char genre;
    
    protected Medium() {
        nbrConsultation=0;
    }
    
    public Medium(String denomination, String presentation, String type, char genre) {
        this. denomination= denomination;
        this.presentation = presentation;
        this.type = type;
        this.genre = genre;
        nbrConsultation=0;
    }
    
    public Long getId() {
        return id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
    
    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getnbrConsultation() {
        return nbrConsultation;
    }

    public void setnbrConsultation(int nbrConsultation) {
        this.nbrConsultation = nbrConsultation;
    }
    
    public void setGenre(char genre){
        this.genre = genre;
    }
    public char getGenre(){
        return genre;
    }
    
    @Override
    public String toString() {
        return "Employee : id=" + id + ", denomination=" + denomination + ", presentation=" + presentation + ", type=" + type + ", nbrConsultation=" + nbrConsultation;
    }
    
}

