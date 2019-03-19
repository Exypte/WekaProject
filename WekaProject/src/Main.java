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
			j48.buildClassifier(instances);
			System.out.println(j48.graph());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
