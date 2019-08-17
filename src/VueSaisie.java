
//import fr.ipst.io.Clavier;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class VueSaisie {

    public FormAutoroute lire() {
        System.out.print("Nombre de voitures : ");
        int nb_v = 5;
        System.out.print("Nombre de caisses : ");
        int nb_c = 5;
        System.out.print("Kilométrage min : ");
        int min = 40;
        System.out.print("Kilométrage max : ");
        int max = 250;
        System.out.print("Vitesse moyenne des véhicules : ");
        int vitesse = 100;
        FormAutoroute f = new FormAutoroute(nb_v, nb_c, min, max, vitesse);
        return f;
    }
}
