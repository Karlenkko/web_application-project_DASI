package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DASI Team
 */
public class AfficherClientEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        boolean consultation = (boolean)request.getAttribute("consultation");
        
        JsonObject container = new JsonObject();
        if(consultation == false){
            container.addProperty("clientExist", Boolean.FALSE);
        } else{
        Client client = (Client)request.getAttribute("client");
        container.addProperty("clientExist", Boolean.TRUE);
        container.addProperty("id",client.getId());
        container.addProperty("mail",client.getMail());
        container.addProperty("nom",client.getNom());
        container.addProperty("prenom",client.getPrenom());
        container.addProperty("tel",client.getTel());
        container.addProperty("adressePostale",client.getAdressePostale());
        container.addProperty("animalTotem",client.getAnimalTotem());
        container.addProperty("couleurPorteBonheur",client.getCouleurPorteBonheur());
        container.addProperty("signeAstrologiqueChinois",client.getSigneAstrologiqueChinois());
        container.addProperty("signeDuZodiaque",client.getSigneDuZodiaque());
        container.addProperty("dateDeNaissance",client.getDateDeNaissance().toString());
        int nb = client.getListcons().size();
        List<Consultation> listcons = client.getListcons();
        
        JsonArray arraycons = new JsonArray();
        for(Consultation cons : listcons){
            JsonObject jsonCons = new JsonObject();
            jsonCons.addProperty("clientNom", cons.getClient().getNom());
            jsonCons.addProperty("clientPrenom", cons.getClient().getPrenom());
            jsonCons.addProperty("mediumDenomination", cons.getMedium().getDenomination());
            jsonCons.addProperty("mediumPresentation", cons.getMedium().getPresentation());
            jsonCons.addProperty("dateDebut", cons.getDateDebut().toString());
            jsonCons.addProperty("dateFin", cons.getDateFin().toString());
            jsonCons.addProperty("employeNom", cons.getEmploye().getNom());
            jsonCons.addProperty("employePrenom", cons.getEmploye().getPrenom());
            jsonCons.addProperty("commentaire", cons.getCommentaire());
            arraycons.add(jsonCons);
        }
        container.add("listcons", arraycons);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
