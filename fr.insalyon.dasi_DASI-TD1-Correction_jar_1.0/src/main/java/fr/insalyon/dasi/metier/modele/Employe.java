/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author 18740
 */
@Entity
public class Employe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String tel;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private char genre;
    private int nbrConsultation;
    private boolean disponible;
    
    
    protected Employe() {
        nbrConsultation=0;
    }

    public Employe(String nom, String prenom, String tel,String mail, String motDePasse, char genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel=tel;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.genre=genre;
        this.disponible=true;
        this.nbrConsultation=0;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public char getGenre() {
        return genre;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }
    
    public int getnbrConsultation() {
        return nbrConsultation;
    }

    public void setnbrConsultation(int nbrConsultation) {
        this.nbrConsultation = nbrConsultation;
    }
    
    public void setDisponiblilite(boolean disponible){
        this.disponible = disponible;
    }
    
    @Override
    public String toString() {
        return "Employe : id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel +", mail=" + mail + ", motDePasse=" + motDePasse+", genre=" + genre+", nbrConsultation=" + nbrConsultation;
    }
    

}

