import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class VuePeage {
	
	private JLabel file;
	private JLabel attente;
	private JPanel caisses;
	
	
	public VuePeage(Gare gare, JLabel attente) {
		
		JFrame fenetre = new JFrame("Simulateur");
		
		Container contenu = fenetre.getContentPane();
        contenu.setLayout(new BorderLayout());
        
        JPanel afficheur = new JPanel(new GridLayout(3, 1));
        contenu.add(afficheur, BorderLayout.SOUTH);
        
        this.file = new JLabel("");
        afficheur.add(file);
        
        this.caisses = new JPanel(new GridLayout(1, gare.toArray().length));
        contenu.add(caisses, BorderLayout.CENTER);
        for(int i = 0; i < gare.toArray().length; i++) {
        	JTextArea caisse = new JTextArea(""+gare.toArray()[i].toString());
        	caisse.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        	Autoroute.getArray_caisses().put(gare.toArray()[i].toString(), caisse);
        	caisses.add(caisse);
        }
        
        this.attente = attente;
		afficheur.add(attente);
        
        //Bouton d'arret
        JButton arret = new JButton("Arrêter");
        arret.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fenetre.dispose();
				System.exit(0);
			}     	
        });
        afficheur.add(arret);
        
        fenetre.setSize(new Dimension(600, 400));
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //fenetre.pack();
        fenetre.setVisible(true);
	}

	public JLabel getFile() {
		return this.file;
	}
	
	public JLabel getAttente() {
		return this.attente;
	}
	
	public void setAttente(JLabel attente) {
		this.attente = attente;
	}
	
	public void setCaisse(JPanel caisses) {
		this.caisses = caisses;
	}
	
}
