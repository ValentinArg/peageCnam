
import java.util.Timer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class Controleur extends Thread{

    private int voitures_restantes;//coiture restant sur l'autoroute
    private Timer timer;

    public Controleur(int voitures_restantes, Timer t) {
        this.voitures_restantes = voitures_restantes;//initaliser le nombre de voiture restante
        timer = t;
    }

    public synchronized void decrement() {//quand une voiture sort, on décrémente le nombre de voiture restantes dans l'autoroute
        voitures_restantes--;
    }

    @Override
    public void run() {
        int i = 0;
        while (voitures_restantes != 0){//on vérifie s'il reste encore des voiture dans l'autoroute
            System.out.print("");
        }
        timer.cancel();//on arrête le timer
    }
}
