/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.text.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class AiderMoiAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long clientId = (Long) session.getAttribute("idClient");
        int amour = Integer.parseInt(request.getParameter("amour"));
        int sante=  Integer.parseInt(request.getParameter("sante"));
        int travail=Integer.parseInt(request.getParameter("travail"));
        Service service = new Service();
        Client client = service.rechercherClientParId(clientId);
        try {
            List<String> s = service.aideMoi(client, amour, sante, travail);
            request.setAttribute("commentaire", s);
            
        } catch (IOException ex) {
            Logger.getLogger(AiderMoiAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        
    }
    
}