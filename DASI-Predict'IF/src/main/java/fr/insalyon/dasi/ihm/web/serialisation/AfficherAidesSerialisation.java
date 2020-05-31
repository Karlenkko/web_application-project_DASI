/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Lenovo
 */
public class AfficherAidesSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Client client = (Client)request.getAttribute("client");
        List<String> commentaire=(List<String>)request.getAttribute("commentaire");
        JsonObject container = new JsonObject();

        Boolean commentaireExiste = (commentaire != null);
        container.addProperty("commentaireExiste", commentaireExiste);

        if (commentaire != null) {
            JsonObject jsonClientCommentaire = new JsonObject();
            jsonClientCommentaire.addProperty("id", client.getId());
            jsonClientCommentaire.addProperty("nom", client.getNom());
            jsonClientCommentaire.addProperty("prenom", client.getPrenom());
            jsonClientCommentaire.addProperty("com_amour",commentaire.get(0));
            jsonClientCommentaire.addProperty("com_sante",commentaire.get(1));
            jsonClientCommentaire.addProperty("com_travail",commentaire.get(2));
            container.add("client_commentaire", jsonClientCommentaire);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }


}
