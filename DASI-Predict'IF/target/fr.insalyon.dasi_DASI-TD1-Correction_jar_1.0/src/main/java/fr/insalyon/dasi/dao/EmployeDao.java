/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lenovo
 */
public class EmployeDao {
    public void creer(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(employe);
    }
    
    public Employe modifier(Employe employe){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(employe);
    }
    
    public Employe chercherParId(Long EmployeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, EmployeId); // renvoie null si l'identifiant n'existe pas
    }
    
    public Employe chercherParMail(String employeMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.mail = :mail", Employe.class);
        query.setParameter("mail", employeMail); // correspond au paramètre ":mail" dans la requête
        List<Employe> employes = query.getResultList();
        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Employe> listerEmployes(){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e", Employe.class);
        return query.getResultList();
    }
    
    public Employe chercherEmployeApproprie(char genre){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query1 = em.createQuery("SELECT e FROM Employe e where e.genre=:genre and e.disponible=true ORDER BY e.nbrConsultation ASC", Employe.class);
        TypedQuery<Employe> query2 = em.createQuery("SELECT e FROM Employe e where e.genre=:genre ORDER BY e.nbrConsultation ASC", Employe.class);
        query1.setParameter("genre", genre);
        query2.setParameter("genre", genre);
        Employe result;
        List<Employe> employes = query1.getResultList();
        if(employes.isEmpty())
            employes=query2.getResultList();
          
        result=employes.get(0);
        return result;
    }
    
    
    
}
