/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.*;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shihang
 */
public class AfficherClientEmployeAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {
        
        Service service = new Service();
        
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("idEmploye");
        
        Employe employe = service.rechercherEmployeParId(id);
        Client client = service.commencerConsultation(employe);
        if(client != null){
            request.setAttribute("consultation", Boolean.TRUE);
            request.setAttribute("client", client);
            session.setAttribute("idClient", client.getId());
        
            Consultation cons = service.rechercherConsultationParEmploye(id);
            session.setAttribute("MediumDeno", cons.getMedium().getDenomination());
            session.setAttribute("idConsultation", cons.getId());
        }else{
            request.setAttribute("consultation", Boolean.FALSE);
        }
        
//        int nb = client.getListcons().size();
//        session.setAttribute("MediumDeno", client.getListcons().get(nb-1).getMedium().getDenomination());
//        session.setAttribute("idConsultation", client.getListcons().get(nb-1).getId());
    }
}
