
import java.awt.*;
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
    
    private JFrame fenetre;
    private static Hashtable<String, JTextArea> array_caisses = new Hashtable<String, JTextArea>();
    private static JLabel label_attente;
    private static long attente_total;
    
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
        
        //Création de la fenêtre
        fenetre = new JFrame("Simulateur");
        Container contenu = fenetre.getContentPane();
        contenu.setLayout(new BorderLayout());
        
        JPanel afficheur = new JPanel(new GridLayout(2, 1));
        contenu.add(afficheur, BorderLayout.SOUTH);
        
        JLabel file = new JLabel();
        afficheur.add(file);
        
        Autoroute.label_attente = new JLabel();
        afficheur.add(Autoroute.label_attente);
        
        JPanel caisses = new JPanel(new GridLayout(1, f.getNb_caisses()));
        contenu.add(caisses, BorderLayout.CENTER);
                
        //Ajout de l'état de la file dans la fenetre
        System.out.println(gare.toString());
        for(int i = 0; i < gare.toArray().length; i++) {
        	JTextArea caisse = new JTextArea(""+gare.toArray()[i].toString());
        	caisse.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        	Autoroute.getArray_caisses().put(gare.toArray()[i].toString(), caisse);
        	caisses.add(caisse);
        }
        Observateur obs = new Observateur(file);
        
        //Lancer le timer
        Timer t = new Timer(true); //timer deamon
        t.schedule(obs, 1000, 1000);
        // CrÃ©er le controleur qui terminera le timer
        Controleur controleur = new Controleur(f.getNb_voitures(), t);
        controleur.start();
        
        //crÃ©ation de la barriÃ¨re de dÃ©part
        CountDownLatch barriere = new CountDownLatch(f.getNb_voitures()); //Attends la création de toutes les voitures
        for (int i = 0; i < f.getNb_voitures(); i++) {
            Voiture v = new Voiture(i, f.getVitesse(), gare, obs, f.getKm_min(), f.getKm_max(), barriere, controleur);
            voitures.add(v);
            v.start();
        }
        
        System.out.print("Fin Main");
        fenetre.setSize(new Dimension(600, 400));
        //fenetre.pack();
        fenetre.setVisible(true);

    }

	public static Hashtable<String, JTextArea> getArray_caisses() {
		return array_caisses;
	}

	public static void setArray_caisses(Hashtable<String, JTextArea> array_caisses) {
		Autoroute.array_caisses = array_caisses;
	}

	public static void increment_attente_total(long attente_total) {
		Autoroute.attente_total += attente_total;
		//System.out.println("Temps d'attente total :" + (float)Autoroute.attente_total);
		Autoroute.label_attente.setText("Temps d'attente moyen : " + (float)Autoroute.attente_total/(float)Autoroute.getNb_voitures() + "ms");
	}

	public static int getNb_voitures() {
		return nb_voitures;
	}

	public static void setNb_voitures(int nb_voitures) {
		Autoroute.nb_voitures = nb_voitures;
	}
	
	
}
