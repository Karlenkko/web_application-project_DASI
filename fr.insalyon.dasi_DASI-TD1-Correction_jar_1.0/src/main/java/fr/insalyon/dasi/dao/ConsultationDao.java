/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 *
 * @author Lenovo
 */
public class ConsultationDao {
    public void creer(Consultation consultation){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(consultation);
    }
    
    public Consultation modifier(Consultation consultation){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(consultation);
    }
    
    public Consultation chercherParId(Long ConsultationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Consultation.class, ConsultationId); // renvoie null si l'identifiant n'existe pas
    }
    
    //quand employe login, lui donner la consultation a faire
    public Consultation chercherConsultationParEmploye(Employe employe){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c WHERE c.fini = false", Consultation.class);
        List<Consultation> consultations = query.getResultList();
        Consultation result = null;
        if (!consultations.isEmpty()) {
            result = consultations.get(0); // premier de la liste
        }
        return result;
    }
}
