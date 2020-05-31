package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomacien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // TODO : Pensez à créer une unité de persistance "DASI-PU" et à vérifier son nom dans la classe JpaUtil
        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();
        Service service = new Service();
        initialiserClients();            // Question 3
        testerInscriptionClient();       // Question 4 & 5
        testerRechercheClient();         // Question 6
        testerListeClients();            // Question 7
        testerAuthentificationClient();  // Question 8
        //saisirInscriptionClient();       // Question 9
        //saisirRechercheClient();
        testerInitialiserEmployee();
        testerAuthentificationEmploye();
        
        
        testerInitialiserMedium();
        
        
        long id;
        id=1;
        Client c1=service.rechercherClientParId(id);
        Medium m1=service.chercherMedium("Gwenaëlle");
        Client c2=service.rechercherClientParId(2L);
        Medium m2=service.chercherMedium("Mme Irma");
        testerFaireConsultation(c1,c2,m1,m2);
        afficherStatisticsAgence("Gwenaëlle");
        
        
        JpaUtil.destroy();
    }
    public static void afficherEmploye(Employe employe) {
        System.out.println("-> " + employe);
    }
    
    public static void afficherMedium(Medium medium){
        System.out.println("-> " + medium);
    }
    
    public static void afficherClient(Client client) {
        System.out.println("-> " + client);
    }

    public static void initialiserClients() throws IOException {
        
        System.out.println();
        System.out.println("**** initialiserClients() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();

        //Client ada = new Client("Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", "Ada1012");
        //Client blaise = new Client("Pascal", "Blaise", "blaise.pascal@insa-lyon.fr", "Blaise1906");
        //Client fred = new Client("Fotiadu", "Frédéric", "frederic.fotiadu@insa-lyon.fr", "INSA-Forever");
        
        Date d1 = new Date(1901-1900,12-1,10);
        Date d2 = new Date(1901-1900,6-1,19);
        Date d3 = new Date(1990-1900,5-1,4);
        Client ada = new Client("Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", "Ada1012",69100,"123456",d1);
        Client blaise = new Client("Pascal", "Blaise", "blaise.pascal@insa-lyon.fr", "Blaise1906",69621,"147258",d2);
        Client fred = new Client("Fotiadu", "Frédéric", "frederic.fotiadu@insa-lyon.fr", "INSA-Forever",67000,"345343",d3);
        System.out.println();
        System.out.println("** Clients avant persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();

        try {
            em.getTransaction().begin();
            em.persist(ada);
            em.persist(blaise);
            em.persist(fred);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }

        System.out.println();
        System.out.println("** Clients après persistance: ");
        afficherClient(ada);
        afficherClient(blaise);
        afficherClient(fred);
        System.out.println();
    }

    public static void testerInscriptionClient() throws IOException {
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Service service = new Service();
        Date d4 = new Date(1988-1900,8-1,4);
        Client claude = new Client("Chappe", "Claude", "claude.chappe@insa-lyon.fr", "HaCKeR",68001, "89498",d4);
        Long idClaude = service.inscrireClient(claude);
        if (idClaude != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(claude);
        Date d5 = new Date(1987-1900,8-1,14);
        Client hedy = new Client("Lamarr", "Hedy", "hlamarr@insa-lyon.fr", "WiFi",69907,"35436",d5);
        Long idHedy = service.inscrireClient(hedy);
        if (idHedy != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(hedy);
        Date d6 = new Date(1977-1900,11-1,4);
        Client hedwig = new Client("Lamarr", "Hedwig Eva Maria", "hlamarr@insa-lyon.fr", "WiFi",67990,"3787534",d6);
        Long idHedwig = service.inscrireClient(hedwig);
        if (idHedwig != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(hedwig);
    }

    public static void testerRechercheClient() {
        
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Client client;

        id = 1;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 3;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client #" + id + " non-trouvé");
        }
    }

    public static void testerListeClients() {
        
        System.out.println();
        System.out.println("**** testerListeClients() ****");
        System.out.println();
        
        Service service = new Service();
        List<Client> listeClients = service.listerClients();
        System.out.println("*** Liste des Clients");
        if (listeClients != null) {
            for (Client client : listeClients) {
                afficherClient(client);
            }
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerAuthentificationClient() {
        
        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client client;
        String mail;
        String motDePasse;

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada1012";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada2020";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "etudiant.fictif@insa-lyon.fr";
        motDePasse = "********";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }

    public static void saisirInscriptionClient() throws IOException {
        Service service = new Service();

        System.out.println();
        System.out.println("Appuyer sur Entrée pour passer la pause...");
        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** NOUVELLE INSCRIPTION **");
        System.out.println("**************************");
        System.out.println();

        String nom = Saisie.lireChaine("Nom ? ");
        String prenom = Saisie.lireChaine("Prénom ? ");
        String mail = Saisie.lireChaine("Mail ? ");
        String motDePasse = Saisie.lireChaine("Mot de passe ? ");
        int adressePostale = Saisie.lireInteger("Adresse postale ? ");
        String tel = Saisie.lireChaine("Tel ? ");
        int year = Saisie.lireInteger("Year ? ");
        int month = Saisie.lireInteger("Month ? ");
        int day = Saisie.lireInteger("Day ? ");
        Date date = new Date(year-1900,month-1,day);
        Client client = new Client(nom, prenom, mail, motDePasse, adressePostale, tel, date);
        Long idClient = service.inscrireClient(client);

        if (idClient != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(client);

    }

    public static void saisirRechercheClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("*********************");
        System.out.println("** MENU INTERACTIF **");
        System.out.println("*********************");
        System.out.println();

        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** RECHERCHE de CLIENTS **");
        System.out.println("**************************");
        System.out.println();
        System.out.println();
        System.out.println("** Recherche par Identifiant:");
        System.out.println();

        Integer idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        while (idClient != 0) {
            Client client = service.rechercherClientParId(idClient.longValue());
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client #" + idClient + " non-trouvé");
            }
            System.out.println();
            idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("********************************");
        System.out.println("** AUTHENTIFICATION de CLIENT **");
        System.out.println("********************************");
        System.out.println();
        System.out.println();
        System.out.println("** Authentifier Client:");
        System.out.println();

        String clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");

        while (!clientMail.equals("0")) {
            String clientMotDePasse = Saisie.lireChaine("Mot de passe ? ");
            Client client = service.authentifierClient(clientMail, clientMotDePasse);
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client non-authentifié");
            }
            clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("*****************");
        System.out.println("** AU REVOIR ! **");
        System.out.println("*****************");
        System.out.println();

    }
    
    public static void testerInitialiserEmployee(){
        Service service = new Service();
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Initialisation des Employee **");
        System.out.println("**************************");
        System.out.println();
        
        Employe e1 = new Employe("PASCAL", "Alice","06 88 77 44 55","alice.pascal@insa-lyon.fr", "alice0206",'F');
        Long ide1 = service.inscrireEmploye(e1);
        if (ide1 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherEmploye(e1);
        
        Employe e2 = new Employe("ZONG","Yuxuan","06 98 82 68 20","yuxuan.zong@insa-lyon.fr","yzong0423",'H');
        Long ide2 = service.inscrireEmploye(e2);
        if (ide2 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherEmploye(e2);
        
        Employe e3 = new Employe("ZHOU","Shihang","06 67 07 29 95","shihang.zhou@insa-lyon.fr","shihang123",'H');
        Long ide3 = service.inscrireEmploye(e3);
        if (ide3 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherEmploye(e3);
        
    }
    
    public static void testerAuthentificationEmploye() {
        
        System.out.println();
        System.out.println("**** testerAuthentificationEmploye() ****");
        System.out.println();
        
        Service service = new Service();
        Employe employe;
        String mail;
        String motDePasse;

        mail = "alice.pascal@insa-lyon.fr";
        motDePasse = "Alice0207";
        employe = service.authentifierEmploye(mail, motDePasse);
        if (employe != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherEmploye(employe);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "yuxuan.zong@insa-lyon.fr";
        motDePasse = "yzong0423";
        employe = service.authentifierEmploye(mail, motDePasse);
        if (employe != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherEmploye(employe);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "shihang.zhou@insa-lyon.fr";
        motDePasse = "********";
        employe = service.authentifierEmploye(mail, motDePasse);
        if (employe != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherEmploye(employe);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }
    
    public static void testerInitialiserMedium(){
        Service service = new Service();
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Initialisation des Mediums **");
        System.out.println("**************************");
        System.out.println();
        
        Medium m1=new Spirite("Gwenaëlle","Spécialiste des grandes conversations au-delà de TOUTES les frontières.","Spirite",'H',"Boule de cristal");
        Medium m2=new Cartomacien("Mme Irma","Comprenez votre entourage grâce à mes cartes ! Résultats rapides","Cartomacien",'F');
        Medium m3=new Astrologue("Serena","Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.","Astrologue",'F',"École Normale Supérieure d’Astrologie (ENS-Astro)","2006");
        
        Long idm1 = service.inscrireMedium(m1);
        if (idm1!= null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherMedium(m1);
        
        Long idm2 = service.inscrireMedium(m2);
        if (idm2!= null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherMedium(m2);
        
        Long idm3 = service.inscrireMedium(m3);
        if (idm3!= null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherMedium(m3);
    }
    
    public static void afficherStatisticsAgence(String denomination){
        Service service = new Service();
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** 1.chercher le nb de consultation avec le medium donnee **");
        System.out.println("**************************");
        System.out.println();
        
        Medium m = service.chercherMedium(denomination);
        if (m!= null) {
            System.out.println(">Succès : le nombre de consultation de "+denomination+" est "+m.getnbrConsultation());
        } else {
            System.out.println("> Échec : Medium non trouve");
        }
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** 2. 5 medium le plus consultee **");
        System.out.println("**************************");
        System.out.println();
        
        List<Medium> lm = service.chercherMediumParNbrdeConsultation();
        if(lm==null){
            System.out.println("> Échec: Medium non trouve");
        }else{
            for(int i=0;i<lm.size();i++){
                System.out.println("No."+(i+1)+" consultee est "+lm.get(i).getDenomination()+" avec une nbr de consultation "+lm.get(i).getnbrConsultation());
            }
        }
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** 3. Repartition de Employee et le nbConsultation **");
        System.out.println("**************************");
        System.out.println();
        
        List<Employe> le = service.listerEmployes();
        if(le==null){
            System.out.println("> Échec: Employe non trouve");
        }else{
            for(int i=0;i<le.size();i++){
                System.out.println("L'employe s'appelle "+le.get(i).getNom()+" avec une nbr de consultation "+le.get(i).getnbrConsultation());
            }
        }
    }
    
    public static void testerFaireConsultation(Client client1, Client client2, Medium medium1, Medium medium2){
        Service service=new Service();
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Commencer à tester une consultation **");
        System.out.println("**************************");
        System.out.println();
    
        long id1=service.demanderConsultation(client1,medium1);
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation demandé **");
        System.out.println("** Client： "+client1.getNom()+" Medium: "+medium1.getDenomination()+" **");
        System.out.println("**************************");
        System.out.println();
        
        Consultation c1 = service.rechercherConsultationParId(id1);

        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation commencé **");
        System.out.println("** Client： "+client1.getNom()+" Employe: "+c1.getEmploye().getNom()+" **");
        System.out.println("**************************");
        System.out.println();
        
        long id2=service.demanderConsultation(client2,medium1);
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation demandé **");
        System.out.println("** Client： "+client2.getNom()+" Medium: "+medium1.getDenomination()+" **");
        System.out.println("**************************");
        System.out.println();
        
        Consultation c2 = service.rechercherConsultationParId(id2);

        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation commencé **");
        System.out.println("** Client： "+client2.getNom()+" Employe: "+c2.getEmploye().getNom()+" **");
        System.out.println("**************************");
        System.out.println();
        
        
        service.finirConsultation(client1,c1.getEmploye(),c1,medium1,"Cette client a un problème mental.");
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation finis **");
        System.out.println("** Commentaire： "+c1.getCommentaire()+" **");
        System.out.println("**************************");
        System.out.println();
        
        
        long id3=service.demanderConsultation(client1,medium1);
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation demandé **");
        System.out.println("** Client： "+client1.getNom()+" Medium: "+medium1.getDenomination()+" **");
        System.out.println("**************************");
        System.out.println();
        
        Consultation c3 = service.rechercherConsultationParId(id3);

        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation commencé **");
        System.out.println("** Client： "+client1.getNom()+" Employe: "+c3.getEmploye().getNom()+" **");
        System.out.println("**************************");
        System.out.println();
        
        
        
        
        
        service.finirConsultation(client2,c2.getEmploye(),c2,medium1,"Cette client est en forme.");
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation finis **");
        System.out.println("** Commentaire： "+c2.getCommentaire()+" **");
        System.out.println("**************************");
        System.out.println();
        
        service.finirConsultation(client1,c3.getEmploye(),c3,medium1,"Cette client n'est plus malade.");
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation finis **");
        System.out.println("** Commentaire： "+c3.getCommentaire()+" **");
        System.out.println("**************************");
        System.out.println();
        
        
        
        ///////////////////
        long id4=service.demanderConsultation(client1,medium2);
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation demandé **");
        System.out.println("** Client： "+client1.getNom()+" Medium: "+medium2.getDenomination()+" **");
        System.out.println("**************************");
        System.out.println();
        
        Consultation c4 = service.rechercherConsultationParId(id4);

        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation commencé **");
        System.out.println("** Client： "+client1.getNom()+" Employe: "+c4.getEmploye().getNom()+" **");
        System.out.println("**************************");
        System.out.println();
        
        long id5=service.demanderConsultation(client2,medium2);
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation demandé **");
        System.out.println("** Client： "+client2.getNom()+" Medium: "+medium2.getDenomination()+" **");
        System.out.println("**************************");
        System.out.println();
        
        Consultation c5 = service.rechercherConsultationParId(id5);

        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation commencé **");
        System.out.println("** Client： "+client2.getNom()+" Employe: "+c5.getEmploye().getNom()+" **");
        System.out.println("**************************");
        System.out.println();
        
        service.finirConsultation(client1,c4.getEmploye(),c4,medium2,"Cette client est en forme4.");
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation finis **");
        System.out.println("** Commentaire： "+c4.getCommentaire()+" **");
        System.out.println("**************************");
        System.out.println();
        /////
        // probleme de phantom read de c5.getEmploye() , il faut ecrire Employe employ1 = new Employe...
        service.finirConsultation(client2,c5.getEmploye(),c5,medium2,"Cette client est en forme5.");
        
        System.out.println();
        System.out.println("**************************");
        System.out.println("** Consultation finis **");
        System.out.println("** Commentaire： "+c5.getCommentaire()+" **");
        System.out.println("**************************");
        System.out.println();
    }
    
}
