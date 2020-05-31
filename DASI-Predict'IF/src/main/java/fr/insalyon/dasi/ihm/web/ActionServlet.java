package fr.insalyon.dasi.ihm.web;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.ihm.web.action.Action;
import fr.insalyon.dasi.ihm.web.action.AfficherClientClientAction;
import fr.insalyon.dasi.ihm.web.action.AfficherClientEmployeAction;
import fr.insalyon.dasi.ihm.web.action.AfficherMediumAction;
import fr.insalyon.dasi.ihm.web.action.AiderMoiAction;
import fr.insalyon.dasi.ihm.web.action.AuthentifierClientAction;
import fr.insalyon.dasi.ihm.web.action.AuthentifierEmployeAction;
import fr.insalyon.dasi.ihm.web.action.ChercherCinqMediumConsulteAction;
import fr.insalyon.dasi.ihm.web.action.ChercherNbConEmployeAction;
import fr.insalyon.dasi.ihm.web.action.ChiffresAction;
import fr.insalyon.dasi.ihm.web.action.CommencerConsultationAction;
import fr.insalyon.dasi.ihm.web.action.FinirConsultationAction;
import fr.insalyon.dasi.ihm.web.action.InscrireClientAction;
import fr.insalyon.dasi.ihm.web.action.ListerMediumAction;
import fr.insalyon.dasi.ihm.web.serialisation.AfficherClientClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.AfficherClientEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.AfficherMediumSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.AiderMoiSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.AuthentifierEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ChercherCinqMediumConsulteSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ChercherNbConEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ChiffresSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.CommencerConmmsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.FinirConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InscrireClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ListerMediumSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ProfilClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.Serialisation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");

        String todo = request.getParameter("todo");

        Action action = null;
        Serialisation serialisation = null;

        if (todo != null) {
            switch (todo) {
                case "connecter-client":
                    action = new AuthentifierClientAction();
                    serialisation = new ProfilClientSerialisation();
                    break;
                case "connecter-employe":
                    action = new AuthentifierEmployeAction();
                    serialisation = new AuthentifierEmployeSerialisation();
                    
                    break;
                case "lister-medium":
                    action = new ListerMediumAction();
                    serialisation = new ListerMediumSerialisation();
                    break;
                case "afficher-client-client":
                    action = new AfficherClientClientAction();
                    serialisation = new AfficherClientClientSerialisation();
                    break;
                case "afficher-client-employe":
                    action = new AfficherClientEmployeAction();
                    serialisation = new AfficherClientEmployeSerialisation();
                    break;
                case "choisir-medium":
                    action = new CommencerConsultationAction();
                    serialisation = new CommencerConmmsultationSerialisation();
                    break;
                case "chercher-medium":
                    action = new AfficherMediumAction();
                    serialisation = new AfficherMediumSerialisation();
                    break;
                    
                case "AiderMoi":
                    action = new AiderMoiAction();
                    serialisation = new AiderMoiSerialisation();
                    break;
                    
                case "fini-consultation":
                    action = new FinirConsultationAction();
                    serialisation = new FinirConsultationSerialisation();
                    break;
                case "chiffres":
                    action = new ChiffresAction();
                    serialisation = new ChiffresSerialisation();
                    break;
                case "inscrire":
                    action = new InscrireClientAction();
                    serialisation = new InscrireClientSerialisation();
                    break;
                case "employes-consulte":
                    action = new ChercherNbConEmployeAction();
                    serialisation = new ChercherNbConEmployeSerialisation();
                    break;
                case "cinq-plus-consulte":
                    action = new ChercherCinqMediumConsulteAction();
                    serialisation = new ChercherCinqMediumConsulteSerialisation();
            }
        }
        
        if (action != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres de la requête");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
