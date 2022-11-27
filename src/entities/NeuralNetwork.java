package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {

    private List<Patient> list = new ArrayList<>();
    private List<Patient> training = new ArrayList<>();
    private double w[] = new double[6];
    private int y = 0;
    private double bias = 0;
    private double wBias;
    private double u = 0;
    private int epoch = 0;
    private int i = 0;
    private double n = 0.005;

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

	for (Patient pt : training) {
	    System.out.println("Age: " + pt.getAge() + " Systolic: " + pt.getSystolicBP());
	}

    }

    public void training() {
	startWeights(w);

	do {
	    
	    for (int i = 0; i < training.size(); i++) {
		activationPotential(u, i, training, w);
		activationFunction(u, y);
	    }
	    
	} while(epoch == 100);

    }

    private static void startWeights(double[] w) {

	Random rand = new Random();

	for (int i = 0; i < w.length; i++) {
	    w[i] = rand.nextDouble(0, 1);
	}

    }

    public static void activationPotential(double u, int i, List<Patient> training, double[] w) {//Potencial de ativação
	u = w[0] * training.get(i).getAge() + w[1] * training.get(i).getSystolicBP()
		+ w[2] * training.get(i).getDiastolicBP() + w[3] * training.get(i).getBS()
		+ w[4] * training.get(i).getBodyTemp() + w[5] * training.get(i).getHeartRaate();
    }

    public static void activationFunction(double u, int y) {//Degrau Bipolar
	if (u > 0)
	    y = 1;
	else if(u == 0)
	    y = 0;
	else
	    y = -1;
    }
    
    public static void recalculatingWeights(double[] w, double n, int y, int i, List<Patient> training) {

	    w[0] = w[0] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getAge();
	    w[1] = w[1] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getSystolicBP();
	    w[2] = w[2] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getDiastolicBP();
	    w[3] = w[3] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getBS();
	    w[4] = w[4] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getBodyTemp();
	    w[5] = w[5] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getHeartRaate();
	
    }
}
