import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			DataSource source = new DataSource("/home/etudiant/weka-3-8-3/data/iris.arff");
			Instances instances = source.getDataSet();
			instances.setClassIndex(instances.numAttributes() - 1);
			System.out.println(instances);
			J48 j48 = new J48();
			j48.setMinNumObj(5);
			j48.buildClassifier(instances);
			System.out.println(j48.graph());
			 Evaluation evaluation = new Evaluation(instances);
			 //evaluation.evaluateModel(j48, instances);
			 evaluation.crossValidateModel(j48, instances, 5, new Random(1));
			 System.out.println(evaluation.toSummaryString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
