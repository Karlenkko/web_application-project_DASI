/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;

/**
 *
 * @author 18740
 */
public class Profile implements Serializable{
    private String signeDuZodiaque;
    private String signeAstrologiqueChinois;
    private String couleurPorteBonheur;
    private String animalTotem;
    
    public Profile(String signeDuZodiaque, String signeAstrologiqueChinois, String couleurPorteBonheur, String animalTotem){
        this.signeDuZodiaque = signeDuZodiaque;
        this.signeAstrologiqueChinois = signeAstrologiqueChinois;
        this.couleurPorteBonheur = couleurPorteBonheur;
        this.animalTotem = animalTotem;
    }
    
    
    public String getSigneDuZodiaque(){
        return signeDuZodiaque;
    }
    public String getSigneAstrologiqueChinois(){
        return signeAstrologiqueChinois;
    }
    public String getCouleurPorteBonheur(){
        return couleurPorteBonheur;
    }
    public String getAnimalTotem(){
        return animalTotem;
    }
    
}
