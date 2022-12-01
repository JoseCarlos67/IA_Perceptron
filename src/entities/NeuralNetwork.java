package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {

    private List<Patient> list = new ArrayList<>();
    private List<Patient> training = new ArrayList<>();
    private double w[] = new double[7];
    private int y = 0;
    private int bias = -1;
    private int epoch = 0;
    private double n = 0.01;

    public NeuralNetwork(List<Patient> list, List<Patient> training) {

	super();
	this.list = list;
	this.training = training;

    }

    public void training() {

	startWeights(w);
	double u = 0;

//	System.out.print("Pessos: ");
//	for (int j = 0; j < w.length; j++) {
//	    System.out.print(w[j] + " ");
//	}


//	System.out.println("\n\nValor de u: \n");

	do {

	    for (int i = 0; i < training.size(); i++) {

		u = 100 / (activationPotential(i, bias, training, w));

//		System.out.println(u + " --- " + training.get(i).getRiskLevel());

		y = activationFunction(u);
		int d = training.get(i).getRiskLevel();
		if (d != y) {
		    recalculatingWeights(w, n, y, i, bias, training);
		    u = 0;
		} else {
		    u = 0;
		}

	    }

	    epoch++;

	} while (epoch != 1000);

//	System.out.println();
//	System.out.println("Acerto: " + acerto);
//	System.out.println("Erro: " + erro);
//	System.out.println();
//
//	System.out.println("Pesos: ");
//	for (int j = 0; j < w.length; j++) {
//
//	    System.out.print(w[j] + " ");

//	}

    }

    public void operationPhase() {

	int mis = 0, hit = 0;

	for (int i = 0; i < list.size(); i++) {

	    double u = 100 / (activationPotential(i, bias, list, w));

	    int y = activationFunction(u);

	    int d = list.get(i).getRiskLevel();

	    if (d != y) {

		mis++;
		recalculatingWeights(w, n, y, i, bias, list);
		u = 0;

	    } else {

		hit++;
		u = 0;

	    }

	}

	double accuracyH = (double)(hit * 100) / list.size();
	double accuracyM = (double)(mis * 100) / list.size();

	System.out.println("Seasons for training = " + epoch);
	System.out.println("Accuracy of hit = " + accuracyH);
	System.out.println("Accuracy of mis = " + accuracyM);
	System.out.println(hit);
	System.out.println(mis);

    }

    // Funções

    private static void startWeights(double[] w) {

	Random rand = new Random();

	for (int i = 0; i < w.length; i++) {
	    w[i] = rand.nextDouble(-1, 1);
	}

    }

    public static double activationPotential(int i, int bias, List<Patient> training, double[] w) {// Potencial de
												   // ativação
	double aux = 0;
	return aux = w[0] * bias + w[1] * training.get(i).getAge() + w[2] * training.get(i).getSystolicBP()
		+ w[3] * training.get(i).getDiastolicBP() + w[4] * training.get(i).getBS()
		+ w[5] * training.get(i).getBodyTemp() + w[6] * training.get(i).getHeartRaate();
    }

    public static int activationFunction(double u) {// Degrau Bipolar
	if (u >= 0)
	    return 1;
	else if (u < 0.009)
	    return -1;
	else
	    return 0;
    }

    public static void recalculatingWeights(double[] w, double n, int y, int i, int bias, List<Patient> training) {

	w[0] = w[0] + n * (training.get(i).getRiskLevel() - y) * bias;
	w[1] = w[1] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getAge();
	w[2] = w[2] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getSystolicBP();
	w[3] = w[3] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getDiastolicBP();
	w[4] = w[4] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getBS();
	w[5] = w[5] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getBodyTemp();
	w[6] = w[6] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getHeartRaate();

    }
}
