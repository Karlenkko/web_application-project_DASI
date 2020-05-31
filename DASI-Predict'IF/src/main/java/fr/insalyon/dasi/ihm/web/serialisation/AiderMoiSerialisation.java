/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
public class AiderMoiSerialisation extends Serialisation{
     @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<String> aides = (List<String>)request.getAttribute("commentaire");
        JsonObject container = new JsonObject();
        Boolean existance = (aides != null);
        container.addProperty("existance", existance);
        if (aides != null) {
            JsonObject jsonCommentaire = new JsonObject();
            
            
            jsonCommentaire.addProperty("prediAmour", aides.get(0));
            jsonCommentaire.addProperty("prediSante", aides.get(1));
            jsonCommentaire.addProperty("prediTravail", aides.get(2));
            container.add("commentaire", jsonCommentaire);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
