import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class vue {
	static JFrame ihm=new JFrame();
	
	vue(String affichage){
		ihm.setTitle("Comparaison d'algorithmes de classification");
		ihm.setLocation(0,0);
		ihm.setSize(1000,500);
		
		
		GridLayout grid=new GridLayout();
		JPanel gridpan=new JPanel();
		grid.setColumns(2);
		grid.setRows(1);
		gridpan.setLayout(grid);
		
		JComboBox<File> jeuxDonnees;
		File jeux = new File("/home/etudiant/weka-3-8-3/data/");
		jeuxDonnees = new JComboBox<>(jeux.listFiles());
		jeuxDonnees.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		
		JTextArea taffichage=new JTextArea (affichage,500,500);
		JScrollPane scroll = new JScrollPane(taffichage);
		
		ihm.add(gridpan);
		gridpan.add(jeuxDonnees);
		
		gridpan.add(taffichage);
		
		ihm.setVisible(true);
	}
}
