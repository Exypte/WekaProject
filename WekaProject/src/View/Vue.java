package View;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Controler.InterfaceControler;
import Model.AlgoJ48;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

public class Vue implements Observateur{

	private JFrame frame;
	private JTextArea tx;
	private JScrollPane sp;

	XYSeries series;
	XYSeriesCollection data;

	private AlgoJ48 j48;
	private InterfaceControler controler;

	public Vue(AlgoJ48 j48, InterfaceControler controler){

		this.j48 = j48;
		this.j48.enregistrerObservateur(this);

		this.controler = controler;

		windowsGenerator();
	}

	public void windowsGenerator() {

		this.frame = new JFrame();
		this.frame.setTitle("Comparaison d'algorithmes de classification");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(new Dimension(1000, 800));
		this.frame.setLocation(1000, 500);

        GridBagConstraints constraints = new GridBagConstraints();
        
		GridLayout mainGrid = new GridLayout(2, 0);
		this.frame.setLayout(mainGrid);		

		GridLayout gridTop = new GridLayout(0, 2);
		JPanel panelTop = new JPanel(gridTop);

		this.tx = new JTextArea();
		this.sp = new JScrollPane(tx);
		panelTop.add(this.sp);

		this.series = new XYSeries("Jeu de donne");
		this.data = new XYSeriesCollection(this.series);
		JFreeChart chart = ChartFactory.createXYLineChart("Pourcentage d'instance correctement classé", "Nombre d'instance", "Pourcentage incorrect", data);
		ChartPanel chartPanel = new ChartPanel(chart);
		panelTop.add(chartPanel);

		GridLayout gridBottom = new GridLayout(0, 4);
		JPanel panelBottom = new JPanel(gridBottom);

		GridLayout splitGrid = new GridLayout(2, 0);
		JPanel splitPanel = new JPanel(splitGrid);
		
		JPanel jpanelStart = new JPanel(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
		Icon icon_run = new ImageIcon("icons/icon_run.png");
		JButton start = new JButton(icon_run);
		jpanelStart.add(start, constraints);
		splitPanel.add(jpanelStart);
        
		JPanel jpanelParcourire = new JPanel(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
		JButton parcourire = new JButton("Parcourire Map");
		jpanelParcourire.add(parcourire, constraints);
		splitPanel.add(jpanelParcourire);
		
		panelBottom.add(splitPanel);
		
		this.frame.add(panelTop);
		this.frame.add(panelBottom);
		this.frame.setVisible(true);

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controler.start();
			}
		});

		parcourire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("data/"));
				
				if (fileChooser.showOpenDialog(frame) == 0) {
					try {
						controler.changementMap(fileChooser.getSelectedFile().getPath());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			}
		});
	}

	@Override
	public void actualiser(ArrayList<String> summaryString, ArrayList<Integer> numInstance, ArrayList<Double> pctIncorrect) {
		// TODO Auto-generated method stub

		this.series.clear();
		this.tx.setText("");

		for(int i = 0; i < summaryString.size(); i++) {
			this.tx.append(summaryString.get(i));
		}

		for(int i = 0; i < numInstance.size(); i++) {
			this.series.add(numInstance.get(i), pctIncorrect.get(i));
		}
	}
}