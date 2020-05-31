package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
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
public class ListerMediumSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Medium> mediums = (List<Medium>)request.getAttribute("mediums");
        JsonObject container = new JsonObject();
        JsonArray listMedium = new JsonArray();

        for(int i = 0; i < mediums.size();i++){
            Medium currMed = mediums.get(i);
            JsonObject jsonMedium = new JsonObject();
            
            jsonMedium.addProperty("id", currMed.getId());
            jsonMedium.addProperty("denomination", currMed.getDenomination());
            jsonMedium.addProperty("genre", currMed.getGenre());
            jsonMedium.addProperty("presentation", currMed.getPresentation());
            jsonMedium.addProperty("type", currMed.getType());
            jsonMedium.addProperty("nbrConsultation", currMed.getnbrConsultation());
            
            listMedium.add(jsonMedium);
        }
        container.add("mediums", listMedium);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
