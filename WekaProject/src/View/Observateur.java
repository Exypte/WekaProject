package View;

import java.util.ArrayList;

public interface Observateur {
	public void actualiser(ArrayList<String> summaryString, ArrayList<Integer> numInstance, ArrayList<Double> pctIncorrect);
}
