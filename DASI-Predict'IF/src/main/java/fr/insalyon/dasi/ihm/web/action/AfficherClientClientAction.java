/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shihang
 */
public class AfficherClientClientAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Service service = new Service();
        Long id = (Long) session.getAttribute("idClient");
        Client client = service.rechercherClientParId(id);
        
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
            request.setAttribute("client", client);

    }
}
