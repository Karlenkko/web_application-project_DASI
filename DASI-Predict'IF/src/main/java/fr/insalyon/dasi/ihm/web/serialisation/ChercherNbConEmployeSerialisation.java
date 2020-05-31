package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Employe;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DASI Team
 */
public class ChercherNbConEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Employe> employes = (List<Employe>)request.getAttribute("employes");
        
        JsonObject container = new JsonObject();
        JsonArray jsonListeEmploye=new JsonArray();
        for(int i=0;i<employes.size();i++){
            
            JsonObject jsonEmploye = new JsonObject();
            
            jsonEmploye.addProperty("id", employes.get(i).getId());
            jsonEmploye.addProperty("nom",employes.get(i).getNom());
            jsonEmploye.addProperty("prenom", employes.get(i).getPrenom());
            jsonEmploye.addProperty("nbrConsultation", employes.get(i).getnbrConsultation());
            
            
            jsonListeEmploye.add(jsonEmploye);
        }
        
        container.add("mediums",jsonListeEmploye);
        
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
