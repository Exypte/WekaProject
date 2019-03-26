import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.opencsv.CSVWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.output.prediction.CSV;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.gui.explorer.VisualizePanel;
import weka.gui.visualize.AttributePanel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		
		try {
			DataSource source = new DataSource("/home/etudiant/weka-3-8-3/data/iris.arff");

			Instances instances = source.getDataSet();
			instances.setClassIndex(instances.numAttributes() - 1);

			//System.out.println(instances);

			J48 j48 = new J48();

			for(int i = 2; i < instances.numInstances()*0.1; i++) {
				
		        List<String[]> data = new ArrayList<String[]>(); 
		        
				j48.setMinNumObj(i);
				j48.buildClassifier(instances);
				
				//System.out.println(j48.graph());
				
				Evaluation evaluation = new Evaluation(instances);
				//evaluation.evaluateModel(j48, instances);
				evaluation.crossValidateModel(j48, instances, 5, new Random(1));
				
				System.out.println(evaluation.toSummaryString());
			  
			    data.add(new String[] {Integer.toString(i), Double.toString(evaluation.pctCorrect())}); 
				writer.writeAll(data);
			}

	        // closing writer connection 
	        writer.close(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
