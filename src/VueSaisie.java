import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//import fr.ipst.io.Clavier;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class VueSaisie {
	
	public VueSaisie(Autoroute auto) {
		JFrame fenetre = new JFrame("Simulateur");
		Container contenu = fenetre.getContentPane();
        contenu.setLayout(new BorderLayout());
        
        JPanel champs = new JPanel(new GridLayout(5,2));
        contenu.add(champs, BorderLayout.CENTER);
        
        JLabel label_nbvoitures = new JLabel("Nombre de voitures :");
        champs.add(label_nbvoitures);
        JTextField field_nbvoitures = new JTextField(10);
        champs.add(field_nbvoitures);
        
        JLabel label_nbcaisses = new JLabel("Nombre de caisses :");
        champs.add(label_nbcaisses);
        JTextField field_nbcaisses = new JTextField(10);
        champs.add(field_nbcaisses);
        
        JLabel label_kmmin = new JLabel("Kilometrage Min :");
        champs.add(label_kmmin);
        JTextField field_kmmin = new JTextField(10);
        champs.add(field_kmmin);
        
        JLabel label_kmmax = new JLabel("Kilometrage Max :");
        champs.add(label_kmmax);
        JTextField field_kmmax = new JTextField(10);
        champs.add(field_kmmax);
        
        JLabel label_vitesse = new JLabel("Vitesse moyenne des vÈhicules :");
        champs.add(label_vitesse);
        JTextField field_vitesse = new JTextField(10);
        champs.add(field_vitesse);
        
        JPanel boutons = new JPanel(new FlowLayout());
        contenu.add(boutons, BorderLayout.SOUTH);
        
        //Bouton de simulation
        JButton lancer = new JButton("Lancer simulation");
        lancer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int nb_voitures = Integer.parseInt(field_nbvoitures.getText());
				int nb_caisses = Integer.parseInt(field_nbcaisses.getText());
				int kmmin = Integer.parseInt(field_kmmin.getText());
				int kmmax = Integer.parseInt(field_kmmax.getText());
				int vitesse = Integer.parseInt(field_vitesse.getText());
				
				auto.simuler(nb_voitures, nb_caisses, kmmin, kmmax, vitesse);
				fenetre.dispose();
			}
        	
        });
        boutons.add(lancer, BorderLayout.CENTER);
        
        //Bouton d'arret
        JButton arret = new JButton("ArrÍter");
        arret.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetre.dispose();
				System.exit(0);
			}     	
        });
        boutons.add(arret, BorderLayout.SOUTH);
        
        fenetre.setSize(new Dimension(600, 400));
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //fenetre.pack();
        fenetre.setVisible(true);
        
	}

    public FormAutoroute lire() {
        System.out.print("Nombre de voitures : ");
        int nb_v = 5;
        System.out.print("Nombre de caisses : ");
        int nb_c = 5;
        System.out.print("Kilom√©trage min : ");
        int min = 40;
        System.out.print("Kilom√©trage max : ");
        int max = 250;
        System.out.print("Vitesse moyenne des v√©hicules : ");
        int vitesse = 100;
        FormAutoroute f = new FormAutoroute(nb_v, nb_c, min, max, vitesse);
        return f;
    }
}
