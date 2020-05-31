/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;
import javax.persistence.Temporal;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author 18740
 */
@Entity
public class Consultation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Client client;
    private Employe employe;
    private Medium medium;
    private String commentaire;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateDebut;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateFin;
    private boolean fini;

    public Consultation() {
    }
    
    public Consultation(Client client, Employe employe, Medium medium, Date date){
        this.client = client;
        this.employe = employe;
        this.medium = medium;
        this.dateDebut = date;
        this.fini = false;
    }
    
    public Long getId() {
        return id;
    }
    
    public Client getClient(){
        return client;
    }
    public Employe getEmploye(){
        return employe;
    }
    
    public Medium getMedium(){
        return medium;
    }
    public String getCommentaire(){
        return commentaire;
    }
    
    public Date getDateDebut(){
        return dateDebut;
    }
    public Date getDateFin(){
        return dateFin;
    }
    
    public void setFini(boolean fini){
        this.fini = fini;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
    public void setDateFin(){
        this.dateFin = new Date();
    }
    
    @Override
    public String toString() {
        return client.toString() + "\n" + medium.toString() + "\n" 
                + dateDebut.toString() + dateFin.toString();
    }
}