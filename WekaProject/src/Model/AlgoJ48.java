package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.opencsv.CSVWriter;

import View.Observateur;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class AlgoJ48 implements Subject{

	private String dataName;
	private  List<Observateur> obs = new ArrayList<Observateur>();
	
	public AlgoJ48(String dataName) {
		this.dataName = dataName;
	}
	
	public void use() {
		// first create file object for file placed at location 
		// specified by filepath 
		File file = new File("test.csv");

		// create FileWriter object with file as parameter 
		FileWriter outputfile = null;
		
		try {
			outputfile = new FileWriter(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}; 

		// create CSVWriter object filewriter object as parameter 
		CSVWriter writer = new CSVWriter(outputfile);

		// create a List which contains String array 
		List<String[]> header = new ArrayList<String[]>(); 
		header.add(new String[] { "Nombre d'exemples min par feuille", "% de bien classé" });
		writer.writeAll(header);

		ArrayList<String> summaryString = new ArrayList<>();
		ArrayList<Integer> numInstance = new ArrayList<>();
		ArrayList<Double> pctIncorrect = new ArrayList<>();
		
		try {
			DataSource source = new DataSource(dataName);

			Instances instances = source.getDataSet();
			instances.setClassIndex(instances.numAttributes() - 1);

			J48 j48 = new J48();

			for(int i = 2; i < instances.numInstances()*0.1; i++) {

				List<String[]> data = new ArrayList<String[]>(); 

				j48.setMinNumObj(i);
				j48.buildClassifier(instances);

				Evaluation evaluation = new Evaluation(instances);
				evaluation.crossValidateModel(j48, instances, 5, new Random(1));

				summaryString.add(evaluation.toSummaryString());
				numInstance.add(i);
				pctIncorrect.add(evaluation.pctIncorrect());

				data.add(new String[] {Integer.toString(i), Double.toString(evaluation.pctIncorrect())}); 
				writer.writeAll(data);
			}

			// closing writer connection 
			writer.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		notifier(summaryString, numInstance, pctIncorrect);
	}

	@Override
	public void enregistrerObservateur(Observateur o) {
		// TODO Auto-generated method stub
		obs.add(o);
	}

	@Override
	public void removeObservateur(Observateur o) {
		// TODO Auto-generated method stub
		obs.remove(o);
	}

	@Override
	public void notifier(ArrayList<String> summaryString, ArrayList<Integer> numInstance, ArrayList<Double> pctIncorrect) {
		// TODO Auto-generated method stub
		for(Observateur o : obs) {
			o.actualiser(summaryString, numInstance, pctIncorrect);
		}
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
}
