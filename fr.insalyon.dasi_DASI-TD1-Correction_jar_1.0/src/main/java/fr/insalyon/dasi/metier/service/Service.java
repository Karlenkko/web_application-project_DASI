package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.AstroTest;
import util.Message;

/**
 *
 * @author DASI Team
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected ConsultationDao consultationDao = new ConsultationDao();
    protected MediumDao mediumDao = new MediumDao();
    
    
    public Employe rechercherEmployeParId(Long id) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherEmployeParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Long inscrireClient(Client client) {
        Long resultat = null;
        Service service = new Service();
        List<Client> listClient = service.listerClients();
        boolean exist = false;
        for(int i = 0; i < listClient.size(); i++){
            if(listClient.get(i).getMail().equalsIgnoreCase(client.getMail())) exist = true;
        }
        JpaUtil.creerContextePersistance();
        if(!exist){
            try {
                JpaUtil.ouvrirTransaction();
                clientDao.creer(client);
                JpaUtil.validerTransaction();
                resultat = client.getId();
            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
                JpaUtil.annulerTransaction();
                resultat = null;
            } finally {
                // send email
                
                JpaUtil.fermerContextePersistance();
            }
        }
        Service.envoyerInscriptionEmail(client, resultat!=null);
        return resultat;
    }
    
    //Appel automatiquement
    public Long inscrireEmploye(Employe employe) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            employeDao.creer(employe);
            JpaUtil.validerTransaction();
            resultat = employe.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireEmploye(employe)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
        public Long inscrireMedium(Medium medium) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            mediumDao.creer(medium);
            JpaUtil.validerTransaction();
            resultat = medium.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireMedium(medium)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Client rechercherClientParId(Long id) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Consultation rechercherConsultationParId(Long id) {
        Consultation resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = consultationDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Consultation rechercherConsultationParEmploye(Long id) {
        Consultation resultat = null;
        Employe emp = null;
        JpaUtil.creerContextePersistance();
        try {
            emp = employeDao.chercherParId(id);
            resultat = consultationDao.chercherConsultationParEmploye(emp);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    
    public Client authentifierClient(String mail, String motDePasse) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

        //Appel par le Login Employee
    public Employe authentifierEmploye(String mail, String motDePasse) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Employe employe= employeDao.chercherParMail(mail);
            if (employe != null) {
                // Vérification du mot de passe
                if (employe.getMotDePasse().equals(motDePasse)) {
                    resultat = employe;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierEmploye(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    //Appel pour voir les details de medium de cote client
    public List<Medium> listerMedium() {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.listerMedium();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMedium()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    //5 plus consultee
    public List<Medium> chercherMediumParNbrdeConsultation(){
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.listerMediumOrderByNb();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherMediumParNbrdeConsultation()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    //chercher le nb de consultation avec le medium donnee
    public Medium chercherMedium(String denomination){
        Medium resultat;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.chercherParDenomination(denomination);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service chercherMedium(String denomination)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }  
        return resultat;
    
    }
    
    public List<Client> listerClients() {
        List<Client> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.listerClients();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    //Repartition de Employee et le nbConsultation
    public List<Employe> listerEmployes(){
        List<Employe> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.listerEmployes();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerEmployes()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Long demanderConsultation(Client client, Medium medium){
        char genre = medium.getGenre();
        Date dateDebut = new Date(System.currentTimeMillis());
        Employe employe = null;
        Consultation consultation = null;
        JpaUtil.creerContextePersistance();
        try{
            JpaUtil.ouvrirTransaction();
            employe = employeDao.chercherEmployeApproprie(genre);
            JpaUtil.validerTransaction();
            consultation = new Consultation(client, employe, medium, dateDebut);
            Service.envoyerConsultationNotification(client, employe, medium);
            employe.setDisponiblilite(false);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderConsultation(Client client, Medium medium)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        // informe client and employe
        
        //update database consultation, employe (update listcons of client , employe, medium later)
        Long resultat;
        JpaUtil.creerContextePersistance();
        try{
            JpaUtil.ouvrirTransaction();
            consultationDao.creer(consultation);
            employeDao.modifier(employe);
            JpaUtil.validerTransaction();
            resultat = consultation.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderConsultation(Client client, Medium medium)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return resultat;
    }
    
    public Client commencerConsultation(Employe employe){
        Client client = null;
        Consultation consultation = null;
        JpaUtil.creerContextePersistance();
        try{
            consultation = consultationDao.chercherConsultationParEmploye(employe);
            client = consultation.getClient();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service commencerConsultation(Employe employe)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;      
    }
    
    public Long finirConsultation(Client client, Employe employe, Consultation consultation, Medium medium, String commentaire){
        // consultation
        consultation.setCommentaire(commentaire);
        consultation.setDateFin();
        consultation.setFini(true);
        //employe
        employe.setDisponiblilite(true);
        employe.setnbrConsultation(employe.getnbrConsultation() + 1);
        //client
        client.addConsultation(consultation);
        //medium
        medium.setnbrConsultation(medium.getnbrConsultation() + 1);
        
        Long resultat = 0L;
        System.out.println(resultat);
        JpaUtil.creerContextePersistance();
        try{
            JpaUtil.ouvrirTransaction();
            consultationDao.modifier(consultation);
            employeDao.modifier(employe);
            clientDao.modifier(client);
            mediumDao.modifier(medium);
            JpaUtil.validerTransaction();
        }catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderConsultation(Client client, Medium medium)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public static void envoyerInscriptionEmail(Client client, boolean success){
        //System.out.println("SENDING" + success + "EMAIL TO " +client.getMail());
        StringWriter corps = new StringWriter();
        PrintWriter mailWriter = new PrintWriter(corps);
        String sujet;
        mailWriter.print("Bonjour ");
        mailWriter.print(client.getPrenom());
        mailWriter.print(" ");
        mailWriter.print(client.getNom());
        if(success){
            sujet = "Bienvenue chez PREDICT’IF";
            mailWriter.print(", nous vous confirmons votre inscription au service PREDICT’IF. "
                    + "Rendezvous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums. ");
        }else{
            sujet = "Echec de l’inscription chez PREDICT’IF";
            mailWriter.print(" , votre inscription au service PREDICT’IF a malencontreusement échoué... "
                    + "Merci de recommencer ultérieurement. ");
        }
        Message.envoyerMail(
                "contact@predict.if",
                client.getMail(),
                sujet,
                corps.toString()
            );
    }
    
    public static void envoyerConsultationNotification(Client client, Employe employe, Medium medium){
        Date now = new Date();
        // message client
        StringWriter messageClient = new StringWriter();
        PrintWriter notificationWriterClient = new PrintWriter(messageClient);
        notificationWriterClient.println("Bonjour " + client.getPrenom() 
                + ". J’ai bien reçu votre demande de consultation du " + now.toLocaleString()
                + ". Vous pouvez dès à présent me contacter au " + employe.getTel()
                + ". A tout de suite ! Médiumiquement vôtre, " + medium.getDenomination());
        Message.envoyerNotification(
                client.getTel(),
                messageClient.toString()
            );
        
        //message employe
        StringWriter messageEmploye = new StringWriter();
        PrintWriter notificationWriterEmploye = new PrintWriter(messageEmploye);
        notificationWriterEmploye.println("Bonjour " + employe.getPrenom()
                                            + ". Consultation requise pour M/Mme " + client.getPrenom()
                                            + " " + client.getNom()+ ". Médium à incarner :"
                                            + medium.getDenomination());
        Message.envoyerNotification(
                employe.getTel(),
                messageEmploye.toString()
        );
    }
    
    public List<String> aideMoi(Client client, int amour, int sante, int travail) throws IOException{
        AstroTest astroApi = new AstroTest();
        String couleur = client.getCouleurPorteBonheur();
        String animal = client.getAnimalTotem();
        List<String> predictions = astroApi.getPredictions(couleur, animal, amour, sante, travail);
        return predictions;
    }
}
