
import java.security.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class Voiture extends Thread {

    private int num;
    private Gare gare;
    private Observateur obs;
    private int parcours;
    private int vitesse;
    private int km_min;
    private int km_max;
    private CountDownLatch barriere;
    private Controleur controleur;

    public Voiture(int num, int v, Gare gare, Observateur obs, int min, int max, CountDownLatch b, Controleur ctrl) {
        super();
        this.num = num;
        this.gare = gare;
        this.obs = obs;
        vitesse = v;
        km_min = min;
        km_max = max;
        barriere = b;
        controleur = ctrl;
    }

    @Override
    public void run() {
        try {
            barriere.countDown();//décrémenter le verrou  
            barriere.await();// attendre que toutes les autres voitures soient à ce même point
            entrer();
            rouler();
            sortir();
            controleur.decrement();//signaler au controleur qu'on est sortie pour décrémenter le nombre de vpoitures restantes dans l'autoroute
        } catch (InterruptedException ex) {
        }
    }

    private void entrer() {
        Random r = new Random();
        parcours = km_min + r.nextInt(km_max - km_min);//générer aléatoirement la longueur du parcours de cette voiture
        //System.out.println("Parcours : "+parcours);

    }

    private void rouler() {
        while (parcours > 0) {//tant que le parcours n'est pas fini
            try {
                parcours--;//avancer d'un cran
                Thread.sleep(vitesse);//à la vitesse choisie
            } catch (InterruptedException ex) {
                
            }
        }
    }

    private void sortir() {
        try {
            obs.increment(); //signaler à l'observateur qu'on attend une caisse
            
            String affichageCaisse = "";
            System.out.println("Voiture " + num + " attend une caisse");
            
            //La voiture demande une caisse
            Date date_avant = new Date();       
            Caisse c = gare.take();//demander une caisse, dès qu'une caisse est libre elle sera affecter à cette voiture et elle sortira du pool de caisses libres
            Date date_apres = new Date();
            Thread.sleep(1);//On attend 1ms pour �viter que laisser le temps � l'affichage de se mettre � jour
            obs.decrement();//signaler à l'observateur qu'on est sorti de la file d'attente d'une caisse
            System.out.println("Voiture " + num + " rentre dans la caisse : "+c.toString());
            Autoroute.setNb_voitures(Autoroute.getNb_voitures() + 1);
            c.increment_Nb_voitures();
            
            long duree_attente = date_apres.getTime() - date_avant.getTime(); 
            Autoroute.maj_attente_moy(duree_attente);           
            affichageCaisse = c.toString()+"\n"+ " Voiture "+num +"\n"+" Nb voitures :"+c.getNb_voitures()+"\n"+"Dur�e moyenne paiement : "+Math.round(c.getDuree_totale_paiement()/c.getNb_voitures())+"ms";
            Autoroute.getArray_caisses().get(c.toString()).setText(affichageCaisse);
            
            //la voiture paye et on met � jour la moyenne de dur�e de paiement
            int duree_paiement = c.payer();//payer
            c.increment_Duree_totale_paiement(duree_paiement);
            System.out.println("Voiture " + num + " dur�e paiement : " + duree_paiement);
            
            //La voiture a termin� et laisse donc la place
            gare.put(c);//libérer la caisse en la remettant dans le pool de caisses libres
            affichageCaisse = c.toString()+ "\n"+"LIBRE"+"\n"+ "Nb voitures :"+c.getNb_voitures()+"\n"+"Dur�e moyenne paiement : "+Math.round(c.getDuree_totale_paiement()/c.getNb_voitures())+"ms";
            Autoroute.getArray_caisses().get(c.toString()).setText(affichageCaisse);
            
        } catch (InterruptedException ex) {
        }
    }
}
