/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lenovo
 */
public class CommencerConsultationEmployeAction extends Action{
     @Override
    public void executer(HttpServletRequest request) {
        Long employeId=Long.parseLong(request.getParameter("mail"));
        
        Service service = new Service();
        Employe employe = service.rechercherEmployeParId(employeId);
        Client client = service.commencerConsultation(employe);
        //request.setAttribute("employe",employe);
        request.setAttribute("client", client);
        HttpSession session = request.getSession();
    }
}
