/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lenovo
 */
public class AfficherAidesAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {
        
        String Clientid = request.getParameter("id");
        String amours = request.getParameter("amour");
        String santes = request.getParameter("sante");
        String travails = request.getParameter("travail");
        int amour=Integer.parseInt(amours);
        int sante=Integer.parseInt(santes);
        int travail = Integer.parseInt(travails);
        long ClientidLong = Long.parseLong(Clientid);
        
        
        Service service = new Service();
        Client client=service.rechercherClientParId(ClientidLong);
        try {
            List<String> commentaire= service.aideMoi(client,amour,sante,travail);
            request.setAttribute("Client", client);
            request.setAttribute("Commentaire",commentaire);
            // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
            HttpSession session = request.getSession();
        } catch (IOException ex) {
            Logger.getLogger(AfficherAidesAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}
