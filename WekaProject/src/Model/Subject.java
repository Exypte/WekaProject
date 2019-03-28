package Model;

import java.util.ArrayList;

import View.Observateur;

public interface Subject {
	 public void enregistrerObservateur(Observateur o);
	 public void removeObservateur(Observateur o);
	 public void notifier(ArrayList<String> summaryString, ArrayList<Integer> numInstance, ArrayList<Double> pctIncorrect);
}
