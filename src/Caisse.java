
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class Caisse {
	
	private int nb_voitures = 0;
	private long duree_totale_paiement = 0;
	
    public int payer(){
        Random r = new Random();
        int n = 1000 + r.nextInt(10000);
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            
        }
		return n;
    }

	public int getNb_voitures() {
		return nb_voitures;
	}

	public void setNb_voitures(int nb_voitures) {
		this.nb_voitures = nb_voitures;
	}
	
	public void increment_Nb_voitures() {
		this.nb_voitures ++;
	}

	public long getDuree_totale_paiement() {
		return duree_totale_paiement;
	}

	public void setDuree_totale_paiement(long duree_totale_paiement) {
		this.duree_totale_paiement = duree_totale_paiement;
	}
	
	public void increment_Duree_totale_paiement(long duree_totale_paiement) {
		this.duree_totale_paiement += duree_totale_paiement;
	}
}
