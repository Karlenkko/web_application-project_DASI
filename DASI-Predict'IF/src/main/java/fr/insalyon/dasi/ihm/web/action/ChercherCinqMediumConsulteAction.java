/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lenovo
 */
public class ChercherCinqMediumConsulteAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {
        
        Service service = new Service();
        List<Medium> mediums = service.chercherMediumParNbrdeConsultation();

        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        request.setAttribute("mediums", mediums);
        
    }
}