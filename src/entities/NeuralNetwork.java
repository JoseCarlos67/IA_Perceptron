package entities;

import java.util.List;

public class NeuralNetwork {

    private double w[] = new double[6];
    private double y = 0;
    private double bias = 0;

    public void generateTrainingList(List<Patient> list, List<Patient> training) {

	int low = 0, mid = 0, high = 0;

	for (Patient pat : list) {
	    if (pat.getRiskLevel() == 0) {
		if (low < 150) {
		    training.add(pat);
		    low++;
		}
	    }
	    if (pat.getRiskLevel() == 1) {
		if (mid < 150) {
		    training.add(pat);
		    mid++;
		}
	    }
	    if (pat.getRiskLevel() == 2) {
		if (high < 150) {
		    training.add(pat);
		    high++;
		}

	    }
	}
	
    }



    public void training() {

    }

}
