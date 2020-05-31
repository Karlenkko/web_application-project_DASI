package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class ChercherNbConMediumAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String denomination = request.getParameter("denomination");

        Service service = new Service();
        Medium medium = service.chercherMedium(denomination);

        request.setAttribute("medium", medium);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
    }
    
}
