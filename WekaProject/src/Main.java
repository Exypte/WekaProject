
import Controler.ControlerCommands;
import Controler.InterfaceControler;
import Model.Algo;
import View.Vue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Algo algo = new Algo("iris.arff");
		
		InterfaceControler controler = new ControlerCommands(algo);
		
		Vue vue = new Vue(algo, controler);
	}
}
