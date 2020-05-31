package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
public class ChercherCinqMediumConsulteSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Medium> medium = (List<Medium>)request.getAttribute("mediums");
        
        JsonObject container = new JsonObject();
        JsonArray jsonListeMedium=new JsonArray();
        for(int i=0;i<medium.size()&&i<5;i++){
            
            JsonObject jsonMedium = new JsonObject();
            
            jsonMedium.addProperty("id", medium.get(i).getId());
            jsonMedium.addProperty("denomination",medium.get(i).getDenomination());
            jsonMedium.addProperty("presentation", medium.get(i).getPresentation());
            jsonMedium.addProperty("nbrConsultation", medium.get(i).getnbrConsultation());
            jsonMedium.addProperty("type", medium.get(i).getType());
            
            jsonListeMedium.add(jsonMedium);
        }
        
        container.add("mediums",jsonListeMedium);
        
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
