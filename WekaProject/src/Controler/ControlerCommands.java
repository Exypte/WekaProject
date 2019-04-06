package Controler;

import Model.Algo;


public class ControlerCommands implements InterfaceControler {

	private Algo algo;

	public ControlerCommands(Algo algo) {
		this.algo = algo;
	}

	@Override
	public void startJ48() {
		// TODO Auto-generated method stub
		algo.algoJ48();
	}

	@Override
	public void startIBK() {
		// TODO Auto-generated method stub
		algo.algoIBK();
	}
	
	@Override
	public void boostingJ48() {
		// TODO Auto-generated method stub
		algo.boostingJ48();
	}

	@Override
	public void boostingIBK() {
		// TODO Auto-generated method stub
		algo.boostingIBK();
	}
	
	@Override
	public void changementMap(String dataName) {
		// TODO Auto-generated method stub
		algo.setDataName(dataName);
	}
}
