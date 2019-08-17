
import java.util.TimerTask;

import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class Observateur extends TimerTask {
    private int compteur = 0;
    private JLabel afficheur;
    
    public Observateur(JLabel afficheur) {
    	this.afficheur = afficheur;
	}

	public synchronized void increment(){
        compteur ++;
    }
    
    public synchronized void decrement(){
        compteur --;
    }
    
    @Override
    public void run() {
        System.out.println(compteur); //Nombre de voitures en attente d'une caisse
        this.afficheur.setText("Voitures dans la file : "+ compteur);
    }
    
}
