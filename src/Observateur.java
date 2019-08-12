
import java.util.TimerTask;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class Observateur extends TimerTask {
    private int compteur = 0;
    
    public synchronized void increment(){
        compteur ++;
    }
    
    public synchronized void decrement(){
        compteur --;
    }
    
    @Override
    public void run() {
        System.out.println(compteur);
    }
    
}
