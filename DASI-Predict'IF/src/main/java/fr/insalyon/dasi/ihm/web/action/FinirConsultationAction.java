/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.*;
import fr.insalyon.dasi.metier.service.Service;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Lenovo
 */
public class FinirConsultationAction extends Action{
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long idClient = (Long) session.getAttribute("idClient");
        Long idEmploye = (Long) session.getAttribute("idEmploye");
        Long idConsultation = (Long) session.getAttribute("idConsultation");
        String commentaire = (String)request.getParameter("commentaire");
        System.out.println(commentaire);
        String Mediumdeno = (String) session.getAttribute("MediumDeno");
        
        Service service = new Service();
        Medium m=service.chercherMedium(Mediumdeno);
        Client c = service.rechercherClientParId(idClient);
        Consultation con = service.rechercherConsultationParId(idConsultation);
        Employe e =service.rechercherEmployeParId(idEmploye);
        System.out.println(m.getDenomination());
        System.out.println(c.getId());
        System.out.println(con.getId());
        System.out.println(e.getId());
        service.finirConsultation(c,e,con,m,commentaire);
    }
}
