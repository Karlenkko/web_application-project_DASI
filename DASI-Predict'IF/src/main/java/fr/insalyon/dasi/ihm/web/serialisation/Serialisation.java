package fr.insalyon.dasi.ihm.web.serialisation;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DASI Team
 */
public abstract class Serialisation {
    
    public abstract void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException;
    
}
