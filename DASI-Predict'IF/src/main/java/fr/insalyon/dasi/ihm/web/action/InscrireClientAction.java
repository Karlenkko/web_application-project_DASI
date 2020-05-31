package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.text.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class InscrireClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String mail = request.getParameter("email");
        String motdePasse = request.getParameter("motdePasse");
        int postal = Integer.parseInt(request.getParameter("postal"));
        String dateNaissance = request.getParameter("dateNaissance");
        String motdePasse2=request.getParameter("motdePasse2");
        String tel = request.getParameter("tel");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Service service = new Service();
        
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateNaissance);
            Client client = null;
            try {
                client = new Client(nom,prenom,mail,motdePasse,postal,tel,date);
                service.inscrireClient(client);
            } catch (IOException ex) {
                Logger.getLogger(InscrireClientAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("client", client);
            request.setAttribute("motdePasse2",motdePasse2);
            HttpSession session = request.getSession();
        }
        catch (ParseException ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors du transformer le date", ex);
        }
    }
    
}