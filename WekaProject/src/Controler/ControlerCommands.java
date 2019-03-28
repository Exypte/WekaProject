package Controler;

import Model.AlgoJ48;


public class ControlerCommands implements InterfaceControler {

	private AlgoJ48 j48;

	public ControlerCommands(AlgoJ48 j48) {
		this.j48 = j48;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		j48.use();
	}

	@Override
	public void changementMap(String dataName) {
		// TODO Auto-generated method stub
		j48.setDataName(dataName);
	}
	
	
}
