/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shihang
 */
public class ChiffresAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Service service = new Service();
        List<Medium> listMediums = service.listerMedium();
        int nbrMedium = listMediums.size();
        List<Client> listClients = service.listerClients();
        int nbrClient = listClients.size();
        List<Employe> listEmployes = service.listerEmployes();
        int nbrEmploye = listEmployes.size();
        
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        request.setAttribute("client", nbrClient);
        request.setAttribute("medium", nbrMedium);
        request.setAttribute("employe", nbrEmploye);
    }
}
