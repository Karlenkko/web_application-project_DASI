package fr.insalyon.dasi.ihm.web.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author DASI Team
 */
public abstract class Action {
    
    public abstract void executer(HttpServletRequest request);
    
}
