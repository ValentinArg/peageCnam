
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class Autoroute {

    private static Autoroute route = null;
    private static HashSet<Voiture> voitures = new HashSet<Voiture>();
    
    private static VuePeage vuepeage;
    private static Hashtable<String, JTextArea> array_caisses = new Hashtable<String, JTextArea>();
    private static JLabel label_attente = new JLabel("");
    private static long attente_total = 0; 
    private static int nb_voitures = 0;

    private Autoroute() {
    }

    public static Autoroute getInstance() {
        if (route == null) {
            route = new Autoroute();
        }
        return route;
    }

    public static void main(String[] args) {
        Autoroute auto = Autoroute.getInstance();
        auto.simuler();
    }

    public void simuler() {
        VueSaisie vue1 = new VueSaisie();
        FormAutoroute f = vue1.lire();
                
        Gare gare = new Gare(f.getNb_caisses());
        
        //CrÈation de la fenÍtre
        Autoroute.vuepeage = new VuePeage(gare, Autoroute.label_attente);     
                
        //Ajout de l'Ètat de la file dans la fenetre
        Observateur obs = new Observateur(Autoroute.vuepeage.getFile());
        
        //Lancer le timer
        Timer t = new Timer(true); //timer deamon
        t.schedule(obs, 1000, 1000);
        // Cr√©er le controleur qui terminera le timer
        Controleur controleur = new Controleur(f.getNb_voitures(), t);
        controleur.start();
        
        //cr√©ation de la barri√®re de d√©part
        CountDownLatch barriere = new CountDownLatch(f.getNb_voitures()); //Attends la crÈation de toutes les voitures
        for (int i = 0; i < f.getNb_voitures(); i++) {
            Voiture v = new Voiture(i, f.getVitesse(), gare, obs, f.getKm_min(), f.getKm_max(), barriere, controleur);
            voitures.add(v);
            v.start();
        }
                
        System.out.print("Fin Main");

    }

	public static Hashtable<String, JTextArea> getArray_caisses() {
		return array_caisses;
	}

	public static void setArray_caisses(Hashtable<String, JTextArea> array_caisses) {
		Autoroute.array_caisses = array_caisses;
	}

	public static void maj_attente_moy(long attente_total) {
		Autoroute.attente_total += attente_total;
		//System.out.println("Temps d'attente total :" + (float)Autoroute.attente_total);
		Autoroute.label_attente.setText("Temps d'attente moyen : " + Math.round(Autoroute.attente_total/Autoroute.getNb_voitures()) + "ms");
		Autoroute.vuepeage.setAttente(Autoroute.label_attente);
	}

	public static int getNb_voitures() {
		return nb_voitures;
	}

	public static void setNb_voitures(int nb_voitures) {
		Autoroute.nb_voitures = nb_voitures;
	}
	
	
}
