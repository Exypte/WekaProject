package View;
import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Controler.InterfaceControler;
import Model.Algo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

public class Vue implements Observateur{

	private JFrame frame;
	private JTextArea tx;
	private JScrollPane sp;
	private JRadioButton rb;

	XYSeries series;
	XYSeriesCollection data;

	private Algo algo;
	private InterfaceControler controler;

	private JFreeChart chart;
	private ChartPanel chartPanel;
	
	private String saveAlgoName;
	
	public Vue(Algo j48, InterfaceControler controler){

		this.algo = j48;
		this.algo.enregistrerObservateur(this);

		this.controler = controler;

		windowsGenerator();
	}

	public void windowsGenerator() {

		this.frame = new JFrame();
		this.frame.setTitle("Comparaison d'algorithmes de classification");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(new Dimension(1000, 800));
		this.frame.setLocation(200, 200);

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
		chart = ChartFactory.createXYLineChart("Pourcentage d'instance mal classé", "Nombre d'instance", "Pourcentage incorrect", data);
		chartPanel = new ChartPanel(chart);
		panelTop.add(chartPanel);

		GridLayout gridBottom = new GridLayout(0, 4);
		JPanel panelBottom = new JPanel(gridBottom);

		GridLayout splitGrid = new GridLayout(2, 0);
		JPanel splitPanel = new JPanel(splitGrid);
		
		JPanel jpanelStart = new JPanel(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
		JButton startJ48 = new JButton("Algo J48");
		jpanelStart.add(startJ48, constraints);
		splitPanel.add(jpanelStart);
		
        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 10, 10);
         
        // add components to the panel
        constraint.gridx = 0;
        constraint.gridy = 0;     
        JLabel labelMin = new JLabel("Min obj : ");
        newPanel.add(labelMin, constraint);
 
        constraint.gridx = 1;
        JTextField tfMin = new JTextField(2);
        tfMin.setText("2");
        newPanel.add(tfMin, constraint);
        
        constraint.gridx = 0;
        constraint.gridy = 1;
        JLabel labelMax = new JLabel("Max obj : ");
        newPanel.add(labelMax, constraint);
         
        constraint.gridx = 1;
        JTextField tfMax = new JTextField(2);
        tfMax.setText("10");
        newPanel.add(tfMax, constraint);
         
        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Nombre d'instance :"));
		
        splitPanel.add(newPanel);
        
		panelBottom.add(splitPanel);
		
		splitPanel = new JPanel(splitGrid);
		
		jpanelStart = new JPanel(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
		JButton startIBK = new JButton("Algo IBK");
		jpanelStart.add(startIBK, constraints);
		splitPanel.add(jpanelStart);
		
        // create a new panel with GridBagLayout manager
        newPanel = new JPanel(new GridBagLayout());
         
        constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 10, 10);
         
        // add components to the panel
        constraint.gridx = 0;
        constraint.gridy = 0;     
        JLabel labelMinVoisin = new JLabel("Min voisin : ");
        newPanel.add(labelMinVoisin, constraint);
 
        constraint.gridx = 1;
        JTextField tfMinVoisin = new JTextField(2);
        tfMinVoisin.setText("2");
        newPanel.add(tfMinVoisin, constraint);
        
        constraint.gridx = 0;
        constraint.gridy = 1;
        JLabel labelMaxVoisin = new JLabel("Max voisin : ");
        newPanel.add(labelMaxVoisin, constraint);
         
        constraint.gridx = 1;
        JTextField tfMaxVoisin = new JTextField(2);
        tfMaxVoisin.setText("10");
        newPanel.add(tfMaxVoisin, constraint);
         
        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Nombre de voisin :"));
        
        splitPanel.add(newPanel);
        
		panelBottom.add(splitPanel);
		
		splitPanel = new JPanel(splitGrid);
		
		jpanelStart = new JPanel(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
		JButton startBoostingJ48 = new JButton("Algo Boosting J48");
		jpanelStart.add(startBoostingJ48, constraints);
		splitPanel.add(jpanelStart);
		
		jpanelStart = new JPanel(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
		JButton startBoostingIBK = new JButton("Algo Boosting IBK");
		jpanelStart.add(startBoostingIBK, constraints);
		splitPanel.add(jpanelStart);
		
		panelBottom.add(splitPanel);
		
		splitPanel = new JPanel(splitGrid);
		
		JPanel jpanelParcourire = new JPanel(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
		JButton parcourire = new JButton("Parcourire jeux de donnés");
		jpanelParcourire.add(parcourire, constraints);
		splitPanel.add(jpanelParcourire);
		
		this.rb = new JRadioButton("Nouveau graph !");
		splitPanel.add(this.rb);
		
		panelBottom.add(splitPanel);
		
		this.frame.add(panelTop);
		this.frame.add(panelBottom);
		this.frame.setVisible(true);

		startJ48.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkIfInt(tfMax.getText(), tfMin.getText())) {
					algo.setMin(Integer.parseInt(tfMin.getText()));
					algo.setMax(Integer.parseInt(tfMax.getText()));
					saveAlgoName = "AlgoJ48 min = " + tfMin.getText() + " max = " + tfMax.getText();
					controler.startJ48();
				}
			}
		});
		
		startIBK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkIfInt(tfMaxVoisin.getText(), tfMinVoisin.getText())) {
					algo.setMin(Integer.parseInt(tfMinVoisin.getText()));
					algo.setMax(Integer.parseInt(tfMaxVoisin.getText()));
					saveAlgoName = "AlgoIBK min = " + tfMinVoisin.getText() + " max = " + tfMaxVoisin.getText();
					controler.startIBK();
				}
			}
		});

		startBoostingJ48.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkIfInt(tfMax.getText(), tfMin.getText())) {
					algo.setMin(Integer.parseInt(tfMin.getText()));
					algo.setMax(Integer.parseInt(tfMax.getText()));
					saveAlgoName = "AlgoBoostingJ48 min = " + tfMin.getText() + " max = " + tfMax.getText();
					controler.boostingJ48();
				}
			}
		});
		
		startBoostingIBK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkIfInt(tfMaxVoisin.getText(), tfMinVoisin.getText())) {
					algo.setMin(Integer.parseInt(tfMinVoisin.getText()));
					algo.setMax(Integer.parseInt(tfMaxVoisin.getText()));
					saveAlgoName = "AlgoBoostingIBK min = " + tfMinVoisin.getText() + " max = " + tfMaxVoisin.getText();
					controler.boostingIBK();
				}
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
		
		if(!this.rb.isSelected()) {
			this.series.clear();
			this.tx.setText("");

			for(int i = 0; i < summaryString.size(); i++) {
				this.tx.append(summaryString.get(i));
			}

			for(int i = 0; i < numInstance.size(); i++) {
				this.series.add(numInstance.get(i), pctIncorrect.get(i));
			}
		}else {
			nouveauGraph(numInstance, pctIncorrect);
		}
	}
	
	public void nouveauGraph(ArrayList<Integer> numInstance, ArrayList<Double> pctIncorrect) {
	    
		XYSeries seriesNew = new XYSeries("Jeu de donne");
		
	    for(int i = 0; i < numInstance.size(); i++) {
			seriesNew.add(numInstance.get(i), pctIncorrect.get(i));
		}
	    
	    XYSeriesCollection dataNew = new XYSeriesCollection(seriesNew);
	    JFreeChart chart = ChartFactory.createXYLineChart("Pourcentage d'instance mal classé", "Nombre d'instance", "Pourcentage incorrect", dataNew);

	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	    JFrame frameNew = new JFrame();
		frameNew.setTitle(this.saveAlgoName);
		//frameNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameNew.setSize(new Dimension(500, 500));
		frameNew.setLocation(1200, 200);
		frameNew.add(chartPanel);
		frameNew.setVisible(true);
	}
	
	boolean checkIfInt(String min, String max) {
		
		boolean isNum = true;
		
		try {
			Integer.parseInt(min);
			Integer.parseInt(max);
		}catch (NumberFormatException e) {
			// TODO: handle exception
			isNum = false;
		}
		
		return isNum;
	}
}