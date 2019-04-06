package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import View.Observateur;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Algo implements Subject{

	private String dataName;
	private int min, max;
	private  List<Observateur> obs;
	
	
	public Algo(String dataName) {
		this.dataName = dataName;
		
		this.obs = new ArrayList<>();
	}
	
	public void algoJ48() {

		ArrayList<String> summaryString = new ArrayList<>();
		ArrayList<Integer> numInstance = new ArrayList<>();
		ArrayList<Double> pctIncorrect = new ArrayList<>();
		
		try {
			DataSource source = new DataSource(dataName);

			Instances instances = source.getDataSet();
			instances.setClassIndex(instances.numAttributes() - 1);

			J48 j48 = new J48();

			//instances.numInstances()*0.1
			
			for(int i = min; i < max; i++) {

				j48.setMinNumObj(i);
				j48.buildClassifier(instances);

				Evaluation evaluation = new Evaluation(instances);
				evaluation.crossValidateModel(j48, instances, 5, new Random(1));

				summaryString.add("\n Iteration sur le nombre d'instance " + i + "\n" + evaluation.toSummaryString());
				numInstance.add(i);
				pctIncorrect.add(evaluation.pctIncorrect());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		notifier(summaryString, numInstance, pctIncorrect);
	}
	
	public void algoIBK() {

		ArrayList<String> summaryString = new ArrayList<>();
		ArrayList<Integer> numInstance = new ArrayList<>();
		ArrayList<Double> pctIncorrect = new ArrayList<>();
		
		try {
			DataSource source = new DataSource(dataName);

			Instances instances = source.getDataSet();
			instances.setClassIndex(instances.numAttributes() - 1);

			IBk iBk = new IBk();

			for(int i = min; i < max; i++) {

				iBk.setKNN(i);
				iBk.buildClassifier(instances);

				Evaluation evaluation = new Evaluation(instances);
				evaluation.crossValidateModel(iBk, instances, 5, new Random(1));

				summaryString.add("\n Iteration sur le nombre de voisin " + i + "\n" + evaluation.toSummaryString());
				numInstance.add(i);
				pctIncorrect.add(evaluation.pctIncorrect());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		notifier(summaryString, numInstance, pctIncorrect);
	}
	
	public void boostingJ48() {

		ArrayList<String> summaryString = new ArrayList<>();
		ArrayList<Integer> numInstance = new ArrayList<>();
		ArrayList<Double> pctIncorrect = new ArrayList<>();
		
		try {
			DataSource source = new DataSource(dataName);

			Instances instances = source.getDataSet();
			instances.setClassIndex(instances.numAttributes() - 1);

			AdaBoostM1 ada = new AdaBoostM1();

			J48 j48 = new J48();

			for(int i = min; i < max; i++) {

				j48.setMinNumObj(i);
				j48.buildClassifier(instances);
				ada.setClassifier(j48);
				
				Evaluation evaluation = new Evaluation(instances);
				evaluation.crossValidateModel(ada, instances, 5, new Random(1));

				summaryString.add("\n Iteration sur le nombre d'instance " + i + "\n" + evaluation.toSummaryString());
				numInstance.add(i);
				pctIncorrect.add(evaluation.pctIncorrect());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		notifier(summaryString, numInstance, pctIncorrect);
	}
	
	public void boostingIBK() {

		ArrayList<String> summaryString = new ArrayList<>();
		ArrayList<Integer> numInstance = new ArrayList<>();
		ArrayList<Double> pctIncorrect = new ArrayList<>();
		
		try {
			DataSource source = new DataSource(dataName);

			Instances instances = source.getDataSet();
			instances.setClassIndex(instances.numAttributes() - 1);

			AdaBoostM1 ada = new AdaBoostM1();
			
			IBk iBk = new IBk();

			for(int i = min; i < max; i++) {

				iBk.setKNN(i);
				iBk.buildClassifier(instances);
				ada.setClassifier(iBk);

				Evaluation evaluation = new Evaluation(instances);
				evaluation.crossValidateModel(ada, instances, 5, new Random(1));

				summaryString.add("\n Iteration sur le nombre de voisin " + i + "\n" + evaluation.toSummaryString());
				numInstance.add(i);
				pctIncorrect.add(evaluation.pctIncorrect());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		notifier(summaryString, numInstance, pctIncorrect);
	}
	
	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
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
}
