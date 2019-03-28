import java.util.concurrent.TimeUnit;

import Controler.ControlerCommands;
import Controler.InterfaceControler;
import Model.AlgoJ48;
import View.Vue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlgoJ48 j48 = new AlgoJ48("data/iris.arff");
		InterfaceControler controler = new ControlerCommands(j48);
		Vue vue = new Vue(j48, controler);
	}
}
