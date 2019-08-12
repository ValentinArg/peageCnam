/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class FormAutoroute {

    private int nb_voitures;
    private int nb_caisses;
    private int km_min;
    private int km_max;
    private int vitesse;

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public FormAutoroute(int nb_voitures, int nb_caisses, int km_min, int km_max, int v) {
        this.nb_voitures = nb_voitures;
        this.nb_caisses = nb_caisses;
        this.km_min = km_min;
        this.km_max = km_max;
        vitesse = v;
    }

    public int getKm_max() {
        return km_max;
    }

    public void setKm_max(int km_max) {
        this.km_max = km_max;
    }

    public int getKm_min() {
        return km_min;
    }

    public void setKm_min(int km_min) {
        this.km_min = km_min;
    }

    public int getNb_caisses() {
        return nb_caisses;
    }

    public void setNb_caisses(int nb_caisses) {
        this.nb_caisses = nb_caisses;
    }

    public int getNb_voitures() {
        return nb_voitures;
    }

    public void setNb_voitures(int nb_voitures) {
        this.nb_voitures = nb_voitures;
    }

}
