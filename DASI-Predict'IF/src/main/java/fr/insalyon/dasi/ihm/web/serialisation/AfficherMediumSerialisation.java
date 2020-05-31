/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
public class AfficherMediumSerialisation extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Medium medium = (Medium)request.getAttribute("medium");
        
        JsonObject container = new JsonObject();

        Boolean trouve = (medium != null);
        container.addProperty("trouve", trouve);

        if (medium != null) {
            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("id", medium.getId());
            jsonMedium.addProperty("denomination", medium.getDenomination());
            jsonMedium.addProperty("presentation", medium.getPresentation());
            jsonMedium.addProperty("type", medium.getType());
            jsonMedium.addProperty("nbrConsultation", medium.getnbrConsultation());
            if (medium instanceof Astrologue) {
                Astrologue a = (Astrologue) medium;
                jsonMedium.addProperty("formation", a.getFormation());
                jsonMedium.addProperty("promotion", a.getPromotion());
            }else if(medium instanceof Spirite){
                Spirite s = (Spirite) medium;
                jsonMedium.addProperty("support", s.getSupport());
            }

            container.add("medium", jsonMedium);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
