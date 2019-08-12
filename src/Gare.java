
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class Gare extends LinkedBlockingQueue<Caisse>{

    public Gare(int n) {
        super(n);
        for(int i=0; i < n; i++){
            Caisse c = new Caisse();
            try {
                put(c);
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
}
