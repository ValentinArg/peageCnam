
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class Caisse {
    public void payer(){
        Random r = new Random();
        int n = 1000 + r.nextInt(10000);
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            
        }
    }
}
