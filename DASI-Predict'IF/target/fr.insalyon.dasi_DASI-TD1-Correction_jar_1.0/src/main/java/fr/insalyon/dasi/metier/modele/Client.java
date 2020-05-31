package fr.insalyon.dasi.metier.modele;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import util.AstroTest;

/**
 *
 * @author DASI Team
 */
@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String tel;
    private String mail;
    private String motDePasse;
    
    private int adressePostale;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeNaissance;
    private Profile profile;
    
    @OneToMany
    private List<Consultation> listcons;
    
    protected Client() {
    }

    public Client(String nom, String prenom, String mail, String motDePasse, int adressePostale, String tel, Date dateDeNaissance) throws IOException {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.adressePostale = adressePostale;
        this.tel = tel;
        this.dateDeNaissance = dateDeNaissance;
        this.listcons = new ArrayList<>();
        AstroTest at = new AstroTest();
        List<String> prof = at.getProfil(prenom, dateDeNaissance);
        this.profile = new Profile (prof.get(0), prof.get(1), prof.get(2), prof.get(3));
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public void setTel(String tel){
        this.tel = tel;
    }
    public String getTel(){
        return tel;
    }
    
    public void setAdressePostale (int adressePostale){
        this.adressePostale = adressePostale;
    }
    
    public int getAdressePostale (){
        return adressePostale;
    }
    
    public void setDateDeNaissance(Date dateDeNaissance){
        this.dateDeNaissance = dateDeNaissance;
    }
    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }
    public String getSigneDuZodiaque(){
        return profile.getSigneDuZodiaque();
    }
    public String getSigneAstrologiqueChinois(){
        return profile.getSigneAstrologiqueChinois();
    }
    public String getCouleurPorteBonheur(){
        return profile.getCouleurPorteBonheur();
    }
    public String getAnimalTotem(){
        return profile.getAnimalTotem();
    }
    @Override
    public String toString() {
        return "Client : id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse=" + motDePasse+ ",Date de Naissance"+ dateDeNaissance + ", signeDuZodiaque =" + profile.getSigneDuZodiaque();
    }
    
    public void addConsultation(Consultation consultation){
        this.listcons.add(consultation);
    }
    
    public List<Consultation> getListcons(){
        return listcons;
    }

}
