/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 18740
 */
public class CommencerConsultationAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {
        String denomination = request.getParameter("medium");
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("idClient");
        
        Service service = new Service();
        Medium medium = service.chercherMedium(denomination);
        Client client = service.rechercherClientParId(id);
        service.demanderConsultation(client, medium);
        request.setAttribute("medium", medium);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
    }
}
