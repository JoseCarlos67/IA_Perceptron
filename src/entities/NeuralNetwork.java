package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {

    private List<Patient> list = new ArrayList<>();
    private List<Patient> training = new ArrayList<>();
    private double w[] = new double[6];
    private double y = 0;
    private double bias = 0;
    private double wBias;
    private double u = 0;
    private int epocas = 0;
    private int i = 0;

    public NeuralNetwork(List<Patient> list, List<Patient> training) {
	super();
	this.list = list;
	this.training = training;
    }

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

    public void teste() {
	
	for(Patient pt: training) {
	    System.out.println("Age: " + pt.getAge() + " Systolic: " + pt.getSystolicBP());
	}
	
    }
    
    public void training() {
	startWeights(w);
	
//	for (double w: w) {
//	    System.out.print(w + " ");
//	}
	
	//for ()
	
    }

    private static void startWeights(double[] w) {

	Random rand = new Random();

	for (int i = 0; i < w.length; i++) {
	    w[i] = rand.nextDouble(0, 1);
	}

    }

    public void activationPotential() {
	u = w[0] * training.get(i).getAge() + w[1] * training.get(i).getSystolicBP()
		+ w[2] * training.get(i).getDiastolicBP() + w[3] * training.get(i).getBS()
		+ w[4] * training.get(i).getBodyTemp() + w[5] * training.get(i).getHeartRaate(); 
    }
}
